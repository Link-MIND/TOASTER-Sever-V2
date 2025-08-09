package com.app.toaster.adapter.out.persistence.toast;

import com.app.toaster.out.persistence.toast.QToastEntity;
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
}
