package com.app.toaster.adapter.out.persistence.mainpage;

import com.app.toaster.adapter.out.persistence.clip.QClipEntity;
import com.app.toaster.adapter.out.persistence.toast.QToastEntity;
import com.app.toaster.adapter.out.persistence.user.QUserEntity;
import com.app.toaster.mainpage.vo.CategoryToastCountVO;
import com.app.toaster.mainpage.vo.MainPageVO;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
class MainPageQueryRepositoryImpl implements MainPageQueryRepository{

    private final JPAQueryFactory queryFactory;
    private final QToastEntity toast = QToastEntity.toastEntity;
    private final QClipEntity clip = QClipEntity.clipEntity;
    private final QUserEntity user = QUserEntity.userEntity;


    public Optional<MainPageVO> getMainPageData(Long userId) {
        List<Tuple> results = queryFactory
            .select(
                user.nickname,
                clip.id,
                clip.title,
                // 서브쿼리로 각 집계값 조회
                JPAExpressions.select(toast.count())
                    .from(toast)
                    .where(toast.userId.eq(userId)),
                JPAExpressions.select(toast.count())
                    .from(toast)
                    .where(toast.userId.eq(userId).and(toast.isRead.isTrue())),
                JPAExpressions.select(toast.count())
                    .from(toast)
                    .where(toast.clipId.eq(clip.id))
            )
            .from(user)
            .leftJoin(clip).on(clip.ownerId.eq(user.userId))
            .where(user.userId.eq(userId))
            .fetch();

        if (results.isEmpty()) {
            return Optional.empty();
        }

        // 결과 변환 로직
        return Optional.of(buildMainPageVO(results));
    }

    private MainPageVO buildMainPageVO(List<Tuple> results) {
        // 첫 번째 결과에서 공통 데이터 추출
        Tuple firstResult = results.get(0);

        String nickname = firstResult.get(user.nickname);
        Integer totalToastCount = firstResult.get(4, Integer.class); // 전체 토스트 수
        Integer readToastCount = firstResult.get(5, Integer.class);  // 읽은 토스트 수

        // 클립별 데이터 변환 (null이 아닌 클립들만)
        List<CategoryToastCountVO> categories = results.stream()
            .filter(tuple -> tuple.get(clip.id) != null) // 클립이 있는 경우만
            .map(tuple -> new CategoryToastCountVO(
                tuple.get(clip.id),
                tuple.get(clip.title),
                tuple.get(6, Long.class) // 클립별 토스트 수
            ))
            .collect(Collectors.toList());

        return new MainPageVO(
            nickname != null ? nickname : "닉네임을 찾을 수 없습니다.",
            totalToastCount != null ? totalToastCount : 0,
            readToastCount != null ? readToastCount : 0,
            categories
        );
    }
}
