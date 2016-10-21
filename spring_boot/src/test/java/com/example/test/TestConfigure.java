package com.example.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by zhougb on 2016/8/11.
 */
@Configuration
@Import(TestImportBeanInfinit.class)
public class TestConfigure {
}
