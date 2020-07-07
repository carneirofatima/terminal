package com.fatima.terminal.motorista.service;

import com.fatima.terminal.motorista.repository.RotaDao;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RotaServiceTest {

    @InjectMocks
    private RotaService service;

    @Mock
    private RotaDao dao;



}