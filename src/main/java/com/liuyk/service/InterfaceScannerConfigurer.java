package com.liuyk.service;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.StringUtils;

public class InterfaceScannerConfigurer implements BeanDefinitionRegistryPostProcessor, InitializingBean, ApplicationContextAware {

	private String basePackage;
	private ApplicationContext applicationContext;
	private DefaultListableBeanFactory beanFactory;  
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
		this.beanFactory =  (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		Scanner scanner = new Scanner(registry);
		scanner.setResourceLoader(this.applicationContext);
		scanner.scan(StringUtils.tokenizeToStringArray(this.basePackage,
				ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
	}

	private final class Scanner extends ClassPathBeanDefinitionScanner {

		public Scanner(BeanDefinitionRegistry registry) {
			super(registry);
		}

		@Override
		protected void registerDefaultFilters() {
			boolean acceptAllInterfaces = true;
			if (acceptAllInterfaces) {
				addIncludeFilter(new TypeFilter() {
					@Override
					public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
							throws IOException {
						return true;
					}
				});
			}
		}

		@Override
		protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
			Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
			if (beanDefinitions.isEmpty()) {
				System.err.println("beanDefinitions is empty");
			} else {
				for (BeanDefinitionHolder holder : beanDefinitions) {
					GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
					String beanClassName = definition.getBeanClassName();
					definition.getPropertyValues().add("mapperInterface", beanClassName);
					definition.getPropertyValues().add("beanFactory", beanFactory);
					definition.setBeanClass(InterfaceInstanceBean.class);
				}
			}
			return beanDefinitions;
		}

		@Override
		protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
			return (beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent());
		}
		
	}

}
