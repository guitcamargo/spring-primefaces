package br.com.guitcamargo.springprimefaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringPrimefacesApplicationTests <T extends SpringPrimefacesApplicationTests<?>> {

	@SuppressWarnings("unchecked")
	private final T endpointTest = (T) this;

	private final YamlPropertiesFactoryBean yamlProperties = new YamlPropertiesFactoryBean();

	@Autowired
	protected MockMvc mockMvc;

	protected SpringPrimefacesApplicationTests() {
		super();
		yamlProperties.setResources(
				new ClassPathResource("scenarios/endpoints/" + endpointTest.getClass().getSimpleName() + ".yml"));
	}

	protected String getScenarioRequestBody(final String scenario) throws NullPointerException {
		return yamlProperties.getObject().getProperty(scenario + ".requestBody");
	}

	public static String getIdFromLocation(URI location) {
		return location.getPath().split("/")[location.getPath().split("/").length - 1];
	}
}
