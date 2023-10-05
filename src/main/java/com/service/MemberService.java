package com.service;

import com.dao.MemberDAO;
import com.domain.MemberVO;
import com.dto.MemberDTO;
import com.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

@Log4j2
public enum MemberService {
    INSTANCE;

    private MemberDAO memberDAO;
    private ModelMapper modelMapper;

    MemberService(){
        memberDAO = new MemberDAO();
        modelMapper = MapperUtil.INSTANCE.getInstance();

    }

    public MemberDTO login(String mid, String mpw) throws Exception{
        MemberVO memberVO = memberDAO.getWithPassword(mid, mpw);

        MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);
        return memberDTO;
    }

    public void modifyUuid(String mid, String uuid) throws Exception{
        memberDAO.updateUuid(mid, uuid);
    }

    public MemberDTO getByUUID(String uuid) throws Exception{
        MemberVO memberVo = memberDAO.selectUuid(uuid);

        MemberDTO memberDTO = modelMapper.map(memberVo, MemberDTO.class);

        return  memberDTO;
    }


}
