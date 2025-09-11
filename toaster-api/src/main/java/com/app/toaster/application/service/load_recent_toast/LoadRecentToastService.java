package com.app.toaster.application.service.load_recent_toast;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.toaster.adapter.in.load_recent_toast.RecentSavedToastResponse;
import com.app.toaster.adapter.in.load_recent_toast.RecentSharedToastResponse;
import com.app.toaster.adapter.in.load_recent_toast.RecentToastResponse;
import com.app.toaster.adapter.out.persistence.toast.ToastMapper;
import com.app.toaster.application.port.load_recent_toast.in.LoadRecentToastUseCase;
import com.app.toaster.application.port.load_recent_toast.out.LoadClipPort;
import com.app.toaster.application.port.load_recent_toast.out.LoadRecentToastPort;
import com.app.toaster.application.port.load_user_setting.out.LoadUserPort;
import com.app.toaster.toast.enums.ClipType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoadRecentToastService implements LoadRecentToastUseCase {

	private final LoadRecentToastPort loadRecentToastPort;
	private final LoadClipPort loadClipPort;
	private final LoadUserPort loadUserPort;

	private final static int LIST_SIZE = 15;

	@Override
	@Transactional(readOnly = true)
	public RecentToastResponse getRecentToast(Long userId) {
		List<RecentSavedToastResponse> recentSavedToasts = loadRecentToastPort.loadRecentToast(userId, LIST_SIZE, ClipType.PRIVATE)
			.stream()
			.map(toast -> RecentSavedToastResponse.toResponse(toast, getClipTitle(toast.getClipId())))
			.toList();

		List<RecentSharedToastResponse> recentSharedToasts = loadRecentToastPort.loadRecentToast(userId, LIST_SIZE, ClipType.SHARED)
			.stream()
			.map(toast -> RecentSharedToastResponse.toResponse(toast, getClipTitle(toast.getClipId()), getUserName(toast.getUserId())))
			.toList();

		return new RecentToastResponse(recentSavedToasts, recentSharedToasts);
	}

	private String getClipTitle(Long clipId){
		if(clipId == 0L)
			return "전체클립";
		return loadClipPort.loadClip(clipId).getTitle();
	}

	private String getUserName(Long userId){
		return loadUserPort.findUser(userId).getNickname();
	}
}
