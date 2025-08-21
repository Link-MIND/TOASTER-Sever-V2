package com.app.toaster.application.port.burn_toast.out;

import java.time.LocalDate;
import java.util.List;

public interface ExpiredToastPort {

	List<Long> findExpiredToasts(LocalDate now);
}
