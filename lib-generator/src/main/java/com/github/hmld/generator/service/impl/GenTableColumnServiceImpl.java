package com.github.hmld.generator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.hmld.generator.mapper.GenTableColumnMapper;
import com.github.hmld.generator.service.IGenTableColumnService;
@Service
public class GenTableColumnServiceImpl implements IGenTableColumnService {
  @Autowired
  private GenTableColumnMapper genTableColumnMapper;
  
}
