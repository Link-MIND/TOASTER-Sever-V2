package com.app.toaster.adapter.out.persistence.clip;

import com.app.toaster.toast.enums.ClipType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ClipQueryRepositoryImpl implements ClipQueryRepository{

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    public Integer findMaxPriorityByOwnerId(Long ownerId) {
        QClipEntity clip = QClipEntity.clipEntity;

        Integer maxPriority = queryFactory
            .select(clip.priority.max())
            .from(clip)
            .where(clip.ownerId.eq(ownerId)
                .and(clip.type.eq(ClipType.SHARED)))
            .fetchOne();
        return maxPriority != null ? maxPriority : 0;
    }

    @Override
    public ClipEntity createClipWithMaxPriority(Long userId, String title) {
        // 1. 현재 최대 priority 조회
        Integer maxPriority = findMaxPriorityByOwnerId(userId);

        // 2. 새로운 ClipEntity 생성 및 저장
        ClipEntity clipEntity = ClipEntity.builder()
            .ownerId(userId)
            .priority(maxPriority + 1)
            .title(title)
            .clipType(ClipType.SHARED)
            .build();

        entityManager.persist(clipEntity);
        entityManager.flush(); // 즉시 DB에 반영

        return clipEntity;
    }
}
