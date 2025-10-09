package com.app.toaster.adapter.in.load_shared_clip;

import com.app.toaster.adapter.in.load_shared_clip.response.SharedClipResponseDto;
import com.app.toaster.application.port.in.LoadSharedClipUseCase;
import com.app.toaster.dto.ApiResponse;
import com.app.toaster.exception.Success;
import com.app.toaster.toast.vo.ToastFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "share-clip", description = "공유 클립 관련 API")
@RestController
@RequestMapping("/v2/share-clip")
@RequiredArgsConstructor
public class LoadSharedClipController {

    private final LoadSharedClipUseCase loadSharedClipUseCase;

    @Operation(summary = "공유 클립 조회", description = "공유된 클립의 상세 정보와 포함된 토스트 목록을 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "클립 조회 성공",
                    content = @Content(schema = @Schema(implementation = SharedClipResponseDto.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 클립")
    })
    @GetMapping("/{clipId}")
    public ApiResponse<SharedClipResponseDto> loadSharedClip(
            @Parameter(name = "clipId", description = "조회할 클립 ID", in = ParameterIn.PATH, required = true) @PathVariable Long clipId,
            @PageableDefault(size = 10) Pageable pageable,
            @Parameter(name = "filter", description = "필터 종류 (ALL, UNREAD, BURNED)", in = ParameterIn.QUERY)
            @RequestParam(name = "filter", defaultValue = "ALL") ToastFilter filter
    ) {
        return ApiResponse.success(Success.GET_TOASTS_IN_SHARE_CLIP_SUCCESS, loadSharedClipUseCase.loadSharedClip(clipId, pageable, filter));
    }
}