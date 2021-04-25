package com.example.demo.service;

import javax.persistence.EntityManager;
import com.example.demo.repository.JpaMemberRepository;
import com.example.demo.repository.MemberRepository;
// import com.example.demo.repository.MemoryMemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

  private EntityManager em;

  @Autowired
  public SpringConfig(EntityManager em) {
    this.em = em;
  }
  // private DataSource dataSource;
  // @Autowired
  // public SpringConfig(DataSource dataSource) {
  // this.dataSource = dataSource;
  // }

  @Bean
  public MemberService memberService() {
    return new MemberService(memberRepository());
  }

  @Bean
  public MemberRepository memberRepository() {
    // return new MemoryMemberRepository();
    // return new JdbcMemberRepository(dataSource);
    // return new JdbcTemplateMemberRepository(dataSource);
    return new JpaMemberRepository(em);
  }

}
