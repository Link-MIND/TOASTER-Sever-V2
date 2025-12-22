package com.app.toaster.clip.adapter.out.persistence.jpa;

import com.app.toaster.toast.domain.toast.enums.ClipType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ClipQueryRepositoryImpl implements ClipQueryRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public Integer findMaxPriorityByOwnerId(Long ownerId) {
        QClipEntity clip = QClipEntity.clipEntity;

        return queryFactory
            .select(clip.priority.max().coalesce(0))
            .from(clip)
            .where(clip.ownerId.eq(ownerId)
                .and(clip.type.eq(ClipType.SHARED)))
            .fetchOne();
    }


}
