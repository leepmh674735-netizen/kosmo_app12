package com.witnter.app.notice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.witnter.app.members.MemberDTO;

@Service
public class NoticeService {

	private final NoticeRepository noticeRepository;

	public NoticeService(NoticeRepository noticeRepository) {
		this.noticeRepository = noticeRepository;
	}

	public NoticeDTOResponseDetail detail(Long id) throws Exception {
		Optional<NoticeDTO> result = noticeRepository.findById(id);
		NoticeDTO noticeDTO = result.orElseThrow();

		List<NoticeFileDetailDTO> ar = new ArrayList<>();

		for (NoticeFileDTO f : noticeDTO.getAttaches()) {
			NoticeFileDetailDTO detailDTO = new NoticeFileDetailDTO();
			detailDTO.setOrignalFileName(f.getOriginaFileName());
			detailDTO.setStoreFileName(f.getStroreFileName());
			ar.add(detailDTO);
		}

		// 객체를 생성하는 생성자 파라미터 쉼표(,) 뒤의 유령 공백을 모두 제거했습니다.
		NoticeDTOResponseDetail res = new NoticeDTOResponseDetail(
				noticeDTO.getId(), 
				noticeDTO.getContent(),
				noticeDTO.getCreatedAt(), 
				noticeDTO.isPinned(), 
				noticeDTO.getTitle(), 
				noticeDTO.getUpdatedAt(),
				noticeDTO.getMemberDTO().getUsername(), 
				noticeDTO.getViews(), 
				ar);

		return res;
	}

	public NoticeDTO add(NoticeDTORequestDTO n, MultipartFile[] attach) throws Exception {
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setTitle(n.getTitle());
		noticeDTO.setContent(n.getContent());
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setUsername(n.getUsername());
		noticeDTO.setMemberDTO(memberDTO);

		noticeDTO = noticeRepository.save(noticeDTO);

		return noticeDTO;
	}

	public List<NoticeDTOResponseDTO> list() throws Exception {
		List<NoticeDTO> ar = noticeRepository.findAll();
		List<NoticeDTOResponseDTO> list = new ArrayList<>();
		
		for (NoticeDTO n : ar) {
			NoticeDTOResponseDTO nr = new NoticeDTOResponseDTO(
					n.getId(), 
					n.getMemberDTO().getUsername(), 
					n.getTitle(), 
					n.getViews(), 
					n.getCreatedAt()
			);
			list.add(nr);
		}
		
		return list; // ◀ 메서드 반환문 끝자리에 숨어있던 특수문자 유령 공백 완벽 제거
	}

}