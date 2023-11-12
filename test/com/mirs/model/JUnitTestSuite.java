package com.mirs.model;

import com.mirs.model.services.branch.BranchServiceImplTest;
import com.mirs.model.services.instrument.InstrumentServiceImpl;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@Suite.SuiteClasses({
        BranchServiceImplTest.class,
        InstrumentServiceImpl.class
})
public class JUnitTestSuite {
}
