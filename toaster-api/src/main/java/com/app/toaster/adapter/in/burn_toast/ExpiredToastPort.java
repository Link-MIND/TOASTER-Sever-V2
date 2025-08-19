package com.app.toaster.adapter.in.burn_toast;

import java.time.LocalDate;
import java.util.List;

public interface ExpiredToastPort {

	List<Long> findExpiredToasts(LocalDate now);
}
