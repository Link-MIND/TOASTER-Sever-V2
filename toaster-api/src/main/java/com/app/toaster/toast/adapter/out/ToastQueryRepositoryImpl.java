package com.app.toaster.toast.adapter.out;

import static com.app.toaster.toast.adapter.out.QToastEntity.toastEntity;
import static com.app.toaster.user.adapter.out.persistence.jpa.QUserEntity.userEntity;

import com.app.toaster.clip.adapter.in.load_shared_clip.response.QToastDto;
import com.app.toaster.clip.adapter.in.load_shared_clip.response.ToastDto;
import com.app.toaster.toast.domain.toast.vo.ToastFilter;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ToastQueryRepositoryImpl implements ToastQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final QToastEntity toast = toastEntity;

    @Override
    public long bulkUpdateClipIdByIds(List<Long> toastIds, Long targetClipId) {
        return queryFactory
            .update(toast)
            .set(toast.clipId, targetClipId)
            .where(toast.id.in(toastIds))
            .execute();
    }

    @Override
    public Page<ToastDto> findToastsByClipId(Long clipId, ToastFilter filter, Pageable pageable) {
        // D-Day 계산을 위한 숫자 템플릿
        // SQL 방언에 따라 DATEDIFF 함수 또는 다른 함수를 사용해야 할 수 있습니다. (e.g., H2: DATEDIFF, MySQL: DATEDIFF)
        NumberTemplate<Integer> dDay = Expressions.numberTemplate(Integer.class, "DATEDIFF(DATE({0}), DATE({1}))", toast.burnedAt, LocalDate.now());


        // 수정자 정보 문자열 포맷팅
        StringTemplate modifiedBy = Expressions.stringTemplate(
            "CONCAT({0}, '님이 ', DATE_FORMAT({1}, '%Y.%m.%d'), ' 수정')",
            userEntity.nickname,
            toast.updateAt
        );

        // 필터링 조건
        BooleanBuilder builder = new BooleanBuilder(toast.clipId.eq(clipId));
        switch (filter) {
            case UNREAD -> builder.and(toast.isRead.isFalse()).and(dDay.goe(0));
            case BURNED -> builder.and(toast.isBurned.isTrue());
            case ALL -> { /* 전체 조회 */ }
        }

        List<ToastDto> content = queryFactory
            .select(new QToastDto(
                toast.id,
                modifiedBy,
                toast.title,
                toast.linkUrl,
                dDay
            ))
            .from(toast)
            .join(userEntity).on(toast.userId.eq(userEntity.userId))
            .where(builder)
            .orderBy(toast.updateAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        // Count 쿼리
        Long count = queryFactory
            .select(toast.count())
            .from(toast)
            .where(builder)
            .fetchOne();

        return new PageImpl<>(content, pageable, count == null ? 0 : count);
    }
}