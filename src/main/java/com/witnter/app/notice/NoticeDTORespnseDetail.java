package com.witnter.app.notice;

import java.time.LocalDateTime;
import java.util.List;

public record NoticeDTORespnseDetail(
	     	
		Long id,
		
		String content,
		
		LocalDateTime createAt,
		
		boolean isPinned,
		
		String tiltle,
		
		LocalDateTime updatedAt,
		
		String username,
		
		Long views,
		
		List<NoticeFileDetailDTO> attaches
		
  ) {

}
