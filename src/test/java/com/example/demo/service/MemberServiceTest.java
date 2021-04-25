package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
  MemberService memberService;
  MemoryMemberRepository memberRepository;

  @BeforeEach
  public void BeforeEach() {
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
  }

  @AfterEach
  public void AfterEach() {
    memberRepository.clearStore();
  }

  @Test
  void 회원가입() {
    // given
    Member member = new Member();
    member.setName("Spring");
    // when
    Long saveId = memberService.join(member);
    //
    Member findMember = memberService.findone(saveId).get();
    assertThat(member.getName()).isEqualTo(findMember.getName());

  }

  @Test
  public void 중복_회원_예외() {
    Member member1 = new Member();
    member1.setName("Spring");

    Member member2 = new Member();
    member2.setName("Spring");

    memberService.join(member1);
    IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    // try {
    // memberService.join(member2);
    // fail();
    // } catch (IllegalStateException e) {
    // assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    // }
  }

  @Test
  void findMembers() {

  }

  @Test
  void findone() {

  }
}
