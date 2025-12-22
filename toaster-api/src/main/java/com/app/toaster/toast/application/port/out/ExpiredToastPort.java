package com.app.toaster.toast.application.port.out;

import java.time.LocalDate;
import java.util.List;

public interface ExpiredToastPort {

	List<Long> findExpiredToasts(LocalDate now);
}
