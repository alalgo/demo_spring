package org.alalgo.service;

import org.springframework.stereotype.Service;

@Service
public class RestfulServiceImpl implements RestfulService {

	@Override
	public String search() {
		return "RestfulServiceImpl search()";
	}

}
