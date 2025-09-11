package com.app.toaster.adapter.out.persistence.toast;

import com.app.toaster.adapter.out.persistence.clip.QClipEntity;
import com.app.toaster.toast.enums.ClipType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ToastQueryRepositoryImpl implements ToastQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final QToastEntity toast = QToastEntity.toastEntity;
    private final QClipEntity clip = QClipEntity.clipEntity;

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

    @Override
    public List<ToastEntity> getRecentToast(Long userId, int size, ClipType clipType) {
        var query = queryFactory
            .selectFrom(toast)
            .leftJoin(clip).on(clip.id.eq(toast.clipId))
            .where(toast.userId.eq(userId));

        if (clipType == ClipType.PRIVATE) {
            query.where(clip.id.isNull().or(clip.type.eq(ClipType.PRIVATE)));
        } else if (clipType == ClipType.SHARED) {
            query.where(clip.id.isNotNull().and(clip.type.eq(ClipType.SHARED)));
        }

        return query
            .orderBy(toast.createdAt.desc())
            .limit(size)
            .fetch();
    }

}
