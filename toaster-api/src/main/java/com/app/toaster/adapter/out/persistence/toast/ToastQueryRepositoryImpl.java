package com.app.toaster.adapter.out.persistence.toast;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ToastQueryRepositoryImpl implements ToastQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final QToastEntity toast = QToastEntity.toastEntity;

    @Override
    public long bulkUpdateClipIdByIds(List<Long> toastIds, Long targetClipId) {
        return queryFactory
            .update(toast)
            .set(toast.clipId, targetClipId)
            .where(toast.id.in(toastIds))
            .execute();
    }

    @Override
    public List<ToastEntity> getExpiringToast(Long userId, int size) {
        return queryFactory
            .selectFrom(toast)
            .where(toast.userId.eq(userId))
            .where(toast.isRead.eq(false))
            .where(toast.isTimerEnabled.eq(true))
            .where(toast.isBurned.eq(false))
            .orderBy(toast.burnedAt.asc())
            .limit(size)
            .fetch();
    }
}
