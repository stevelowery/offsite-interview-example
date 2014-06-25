package com.gatesbiz.interview.example.inject;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;

public class OffsiteInterviewExampleModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new JpaPersistModule("example"));
	}

}
