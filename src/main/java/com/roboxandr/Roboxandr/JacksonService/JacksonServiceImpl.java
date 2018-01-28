package com.roboxandr.Roboxandr.JacksonService;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class JacksonServiceImpl implements JacksonService {

	@Override
	public String toJson(Object object) {
		ObjectMapper mapper = new ObjectMapper();
        try {
            String value = mapper.writeValueAsString(object);
            return value;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        } catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
  
}
