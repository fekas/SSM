	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpServletRequest processedRequest = request;
		HandlerExecutionChain mappedHandler = null;
		boolean multipartRequestParsed = false;

		WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);

		try {
			ModelAndView mv = null;
			Exception dispatchException = null;

			try {
				//1.检查文件是否上传
				processedRequest = checkMultipart(request);
				multipartRequestParsed = processedRequest != request;

				// Determine handler for the current request.
				//2.根据当前的请求地址找到处理请求的类
				mappedHandler = getHandler(processedRequest);
				
				//没找到处理请求的类抛异常
				if (mappedHandler == null || mappedHandler.getHandler() == null) {
					noHandlerFound(processedRequest, response);
					return;
				}

				// Determine handler adapter for the current request.
				//3.拿到能执行这个类的方法的适配器(反射工具)
				HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());

				// Process last-modified header, if supported by the handler.
				String method = request.getMethod();
				boolean isGet = "GET".equals(method);
				if (isGet || "HEAD".equals(method)) {
					long lastModified = ha.getLastModified(request, mappedHandler.getHandler());
					if (logger.isDebugEnabled()) {
						String requestUri = urlPathHelper.getRequestUri(request);
						logger.debug("Last-Modified value for [" + requestUri + "] is: " + lastModified);
					}
					if (new ServletWebRequest(request, response).checkNotModified(lastModified) && isGet) {
						return;
					}
				}

				if (!mappedHandler.applyPreHandle(processedRequest, response)) {
					return;
				}

				try {
					// Actually invoke the handler.
					//4.适配器执行目标方法，返回值为ModelAndView
					mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
				}
				finally {
					if (asyncManager.isConcurrentHandlingStarted()) {
						return;
					}
				}
				//5.如果没有视图名(方法返回void)，设置默认视图名
				applyDefaultViewName(request, mv);
				mappedHandler.applyPostHandle(processedRequest, response, mv);
			}
			catch (Exception ex) {
				dispatchException = ex;
			}
			//6.根据方法最终执行完成后封装的ModelAndView转发或者重定向到页面(试图渲染)
			processDispatchResult(processedRequest, response, mappedHandler, mv, dispatchException);
		}
		catch (Exception ex) {
			triggerAfterCompletion(processedRequest, response, mappedHandler, ex);
		}
		catch (Error err) {
			triggerAfterCompletionWithError(processedRequest, response, mappedHandler, err);
		}
		finally {
			if (asyncManager.isConcurrentHandlingStarted()) {
				// Instead of postHandle and afterCompletion
				mappedHandler.applyAfterConcurrentHandlingStarted(processedRequest, response);
				return;
			}
			// Clean up any resources used by a multipart request.
			if (multipartRequestParsed) {
				cleanupMultipart(processedRequest);
			}
		}
	}
	
	
	//返回目标处理器类的执行链
	protected HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
		//handlerMappings：保存了每一个处理器处理那些请求的映射信息，
		//IOC容器启动创建对象时将映射信息保存在HandlerMapping实现类的handleMap属性中
		//(基于注解：DefaultAnnotationHandlerMapping；基于配置：BeanNameUrlHandlerMapping)
		for (HandlerMapping hm : this.handlerMappings) {
			if (logger.isTraceEnabled()) {
				logger.trace(
						"Testing handler map [" + hm + "] in DispatcherServlet with name '" + getServletName() + "'");
			}
			HandlerExecutionChain handler = hm.getHandler(request);
			if (handler != null) {
				return handler;
			}
		}
		return null;
	}
	
	
	//返回执行目标处理器的适配器
	protected HandlerAdapter getHandlerAdapter(Object handler) throws ServletException {
		//handlerAdapters保存了几种适配器
		for (HandlerAdapter ha : this.handlerAdapters) {
			if (logger.isTraceEnabled()) {
				logger.trace("Testing handler adapter [" + ha + "]");
			}
			//判断哪个适配器支持
			if (ha.supports(handler)) {
				return ha;
			}
		}
		throw new ServletException("No adapter for handler [" + handler +
				"]: The DispatcherServlet configuration needs to include a HandlerAdapter that supports this handler");
	}
	
	//可以在web.xml中修改DispatcherServlet某些默认配置，例如字符编码过滤器中的encodeing和forceEncoding
	
	//SpringMVC九大组件
	//SpringMVC在工作的时候关键都是由组件完成
	//九大组件都是接口，接口即是规范，扩展性
	/** MultipartResolver used by this servlet 文件上传解析器*/
	private MultipartResolver multipartResolver;

	/** LocaleResolver used by this servlet 国际化区域信息解析器*/
	private LocaleResolver localeResolver;

	/** ThemeResolver used by this servlet 主题解析器：主题效果更换*/
	private ThemeResolver themeResolver;

	/** List of HandlerMappings used by this servlet handler映射信息*/
	private List<HandlerMapping> handlerMappings;

	/** List of HandlerAdapters used by this servlet handler适配器*/
	private List<HandlerAdapter> handlerAdapters;

	/** List of HandlerExceptionResolvers used by this servlet 异常解析功能*/
	private List<HandlerExceptionResolver> handlerExceptionResolvers;

	/** RequestToViewNameTranslator used by this servlet */
	private RequestToViewNameTranslator viewNameTranslator;

	/** FlashMapManager used by this servlet SpringMVC允许重定向携带数据的功能*/
	private FlashMapManager flashMapManager;

	/** List of ViewResolvers used by this servlet 视图解析器*/
	private List<ViewResolver> viewResolvers;
	
	//九大组件初始化：
	//去容器中找组件，没有读取org.springframework.web.servlet下的DispatcherServlet.properties文件使用默认配置
	//有些组件用类型找，有些组件用id找
	@Override
	protected void onRefresh(ApplicationContext context) {
		initStrategies(context);
	}
	protected void initStrategies(ApplicationContext context) {
		initMultipartResolver(context);
		initLocaleResolver(context);
		initThemeResolver(context);
		initHandlerMappings(context);
		initHandlerAdapters(context);
		initHandlerExceptionResolvers(context);
		initRequestToViewNameTranslator(context);
		initViewResolvers(context);
		initFlashMapManager(context);
	}
	
	
	
	mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
					||
					||
					\/
	return invokeHandlerMethod(request, response, handler);
					||
					||
					\/
	methodInvoker.invokeHandlerMethod(handlerMethod(目标方法), handler(执行处理器), webRequest, implicitModel(隐含模型));

	protected ModelAndView invokeHandlerMethod(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//拿到方法解析器
		ServletHandlerMethodResolver methodResolver = getMethodResolver(handler);
		//方法解析器根据当前请求地址找到真正执行的目标方法
		Method handlerMethod = methodResolver.resolveHandlerMethod(request);
		//创建方法执行器
		ServletHandlerMethodInvoker methodInvoker = new ServletHandlerMethodInvoker(methodResolver);
		//包装原生的request、response
		ServletWebRequest webRequest = new ServletWebRequest(request, response);
		//创建一个BindingAwareModelMap(创建一个隐含模型)
		ExtendedModelMap implicitModel = new BindingAwareModelMap();

		//真正执行目标方法：目标方法利用反射执行期间确定参数值，执行modelAttribute等操作
		Object result = methodInvoker.invokeHandlerMethod(handlerMethod(目标方法), handler(执行处理器), 
			webRequest, implicitModel(隐含模型));
		ModelAndView mav =
				methodInvoker.getModelAndView(handlerMethod, handler.getClass(), result, implicitModel, webRequest);
		methodInvoker.updateModelAttributes(handler, (mav != null ? mav.getModel() : null), implicitModel, webRequest);
		return mav;
	}
	
	public final Object invokeHandlerMethod(Method handlerMethod, Object handler,
			NativeWebRequest webRequest, ExtendedModelMap implicitModel) throws Exception {

		Method handlerMethodToInvoke = BridgeMethodResolver.findBridgedMethod(handlerMethod);
		try {
			boolean debug = logger.isDebugEnabled();
			//处理@SessionAttribute
			for (String attrName : this.methodResolver.getActualSessionAttributeNames()) {
				Object attrValue = this.sessionAttributeStore.retrieveAttribute(webRequest, attrName);
				if (attrValue != null) {
					implicitModel.addAttribute(attrName, attrValue);
				}
			}
			//找到并执行所有@ModelAttribute注解标注的方法
			for (Method attributeMethod : this.methodResolver.getModelAttributeMethods()) {
				Method attributeMethodToInvoke = BridgeMethodResolver.findBridgedMethod(attributeMethod);
				
				//确定@ModelAttribute注解标注的方法执行需要的参数
				Object[] args = resolveHandlerArguments(attributeMethodToInvoke, handler, webRequest, implicitModel(隐含模型));
				if (debug) {
					logger.debug("Invoking model attribute method: " + attributeMethodToInvoke);
				}
				String attrName = AnnotationUtils.findAnnotation(attributeMethod, ModelAttribute.class).value();
				if (!"".equals(attrName) && implicitModel.containsAttribute(attrName)) {
					continue;
				}
				ReflectionUtils.makeAccessible(attributeMethodToInvoke);
				//反射执行@ModelAttribute标注的方法
				Object attrValue = attributeMethodToInvoke.invoke(handler, args);
				//attrName的值为@ModelAttribute的value值
				if ("".equals(attrName)) {	//没有标：设为类名首字母小写
					//解析返回值类型
					Class<?> resolvedType = GenericTypeResolver.resolveReturnType(attributeMethodToInvoke, handler.getClass());
					//为返回值类型取一个变量名(类名首字母小写)
					attrName = Conventions.getVariableNameForReturnType(attributeMethodToInvoke, resolvedType, attrValue);
				}
				//@ModelAttribute的另一个作用，将方法运行完的返回值按照方法上指定的key(attrNam作为key名)放到隐含模型中
				if (!implicitModel.containsAttribute(attrName)) {
					implicitModel.addAttribute(attrName, attrValue);
				}
			}
			//解析目标方法的参数是那些值
			Object[] args = resolveHandlerArguments(handlerMethodToInvoke, handler, webRequest, implicitModel);
			if (debug) {
				logger.debug("Invoking request handler method: " + handlerMethodToInvoke);
			}
			ReflectionUtils.makeAccessible(handlerMethodToInvoke);
			//目标处理器开始运行
			//所以@ModelAttribute标注的方法先于目标处理器运行
			return handlerMethodToInvoke.invoke(handler, args);
		}
		catch (IllegalStateException ex) {
			// Internal assertion failed (e.g. invalid signature):
			// throw exception with full handler method context...
			throw new HandlerMethodInvocationException(handlerMethodToInvoke, ex);
		}
		catch (InvocationTargetException ex) {
			// User-defined @ModelAttribute/@InitBinder/@RequestMapping method threw an exception...
			ReflectionUtils.rethrowException(ex.getTargetException());
			return null;
		}
	}
	
	
	//确定方法运行时使用的每一个参数的值
	private Object[] resolveHandlerArguments(Method handlerMethod, Object handler,
			NativeWebRequest webRequest, ExtendedModelMap implicitModel) throws Exception {

		Class<?>[] paramTypes = handlerMethod.getParameterTypes();
		//创建一个和参数个数一样多的数组来保存参数的值
		Object[] args = new Object[paramTypes.length];

		for (int i = 0; i < args.length; i++) {
			MethodParameter methodParam = new MethodParameter(handlerMethod, i);
			methodParam.initParameterNameDiscovery(this.parameterNameDiscoverer);
			GenericTypeResolver.resolveParameterType(methodParam, handler.getClass());
			String paramName = null;
			String headerName = null;
			boolean requestBodyFound = false;
			String cookieName = null;
			String pathVarName = null;
			String attrName = null;
			boolean required = false;
			String defaultValue = null;
			boolean validate = false;
			Object[] validationHints = null;
			int annotationsFound = 0;
			
			//拿到参数列表上的所有注解
			Annotation[] paramAnns = methodParam.getParameterAnnotations();
			//遍历参数列表上所有的注解并解析和保存注解信息
			for (Annotation paramAnn : paramAnns) {
				//如果有注解保存注解的详细信息
				if (RequestParam.class.isInstance(paramAnn)) {
					RequestParam requestParam = (RequestParam) paramAnn;
					paramName = requestParam.value();
					required = requestParam.required();
					defaultValue = parseDefaultValueAttribute(requestParam.defaultValue());
					annotationsFound++;
				}
				else if (RequestHeader.class.isInstance(paramAnn)) {
					RequestHeader requestHeader = (RequestHeader) paramAnn;
					headerName = requestHeader.value();
					required = requestHeader.required();
					defaultValue = parseDefaultValueAttribute(requestHeader.defaultValue());
					annotationsFound++;
				}
				else if (RequestBody.class.isInstance(paramAnn)) {
					requestBodyFound = true;
					annotationsFound++;
				}
				else if (CookieValue.class.isInstance(paramAnn)) {
					CookieValue cookieValue = (CookieValue) paramAnn;
					cookieName = cookieValue.value();
					required = cookieValue.required();
					defaultValue = parseDefaultValueAttribute(cookieValue.defaultValue());
					annotationsFound++;
				}
				else if (PathVariable.class.isInstance(paramAnn)) {
					PathVariable pathVar = (PathVariable) paramAnn;
					pathVarName = pathVar.value();
					annotationsFound++;
				}
				else if (ModelAttribute.class.isInstance(paramAnn)) {
					ModelAttribute attr = (ModelAttribute) paramAnn;
					attrName = attr.value();
					annotationsFound++;
				}
				else if (Value.class.isInstance(paramAnn)) {
					defaultValue = ((Value) paramAnn).value();
				}
				else if (paramAnn.annotationType().getSimpleName().startsWith("Valid")) {
					validate = true;
					Object value = AnnotationUtils.getValue(paramAnn);
					validationHints = (value instanceof Object[] ? (Object[]) value : new Object[] {value});
				}
			}

			//每个参数最多只能标注一个注解
			if (annotationsFound > 1) {
				throw new IllegalStateException("Handler parameter annotations are exclusive choices - " +
						"do not specify more than one such annotation on the same parameter: " + handlerMethod);
			}
			
			//没有找到任何注解
			if (annotationsFound == 0) {
				//解析普通参数
				//最终也会进入resolveStandardArgument(解析标准参数)
				Object argValue = resolveCommonArgument(methodParam, webRequest);
				if (argValue != WebArgumentResolver.UNRESOLVED) {	//是否原生API？
					args[i] = argValue;
				}
				else if (defaultValue != null) {		//是否有默认值
					args[i] = resolveDefaultValue(defaultValue);
				}
				else {
					Class<?> paramType = methodParam.getParameterType();
					
					if (Model.class.isAssignableFrom(paramType) || Map.class.isAssignableFrom(paramType)) {	//是否是Map或者Model
						if (!paramType.isAssignableFrom(implicitModel.getClass())) {
							throw new IllegalStateException("Argument [" + paramType.getSimpleName() + "] is of type " +
									"Model or Map but is not assignable from the actual model. You may need to switch " +
									"newer MVC infrastructure classes to use this argument.");
						}
						//将隐藏模型赋给参数
						args[i] = implicitModel;
					}
					else if (SessionStatus.class.isAssignableFrom(paramType)) {
						args[i] = this.sessionStatus;
					}
					else if (HttpEntity.class.isAssignableFrom(paramType)) {
						args[i] = resolveHttpEntityRequest(methodParam, webRequest);
					}
					else if (Errors.class.isAssignableFrom(paramType)) {
						throw new IllegalStateException("Errors/BindingResult argument declared " +
								"without preceding model attribute. Check your handler method signature!");
					}
					else if (BeanUtils.isSimpleProperty(paramType)) {		是否简单类型(基本类型封装类)
						paramName = "";
					}
					else {					//POJO赋值为空串
						attrName = "";
					}
				}
			}
			//确定值的环节
			if (paramName != null) {
				args[i] = resolveRequestParam(paramName, required, defaultValue, methodParam, webRequest, handler);
			}
			else if (headerName != null) {
				args[i] = resolveRequestHeader(headerName, required, defaultValue, methodParam, webRequest, handler);
			}
			else if (requestBodyFound) {
				args[i] = resolveRequestBody(methodParam, webRequest, handler);
			}
			else if (cookieName != null) {
				args[i] = resolveCookieValue(cookieName, required, defaultValue, methodParam, webRequest, handler);
			}
			else if (pathVarName != null) {
				args[i] = resolvePathVariable(pathVarName, methodParam, webRequest, handler);
			}
			//确定自定义类型参数的值并将请求中的每一个参数赋值给这个对象的属性
			else if (attrName != null) {
				WebDataBinder binder =
						resolveModelAttribute(attrName, methodParam, implicitModel, webRequest, handler);
				boolean assignBindingResult = (args.length > i + 1 && Errors.class.isAssignableFrom(paramTypes[i + 1]));
				if (binder.getTarget() != null) {
					//将请求中的每一个参数赋值给这个对象的属性
					doBind(binder, webRequest, validate, validationHints, !assignBindingResult);
				}
				args[i] = binder.getTarget();
				if (assignBindingResult) {
					args[i + 1] = binder.getBindingResult();
					i++;
				}
				implicitModel.putAll(binder.getBindingResult().getModel());
			}
		}

		return args;
	}
	//解析普通参数
	protected Object resolveCommonArgument(MethodParameter methodParameter, NativeWebRequest webRequest)
			throws Exception {

		// Invoke custom argument resolvers if present...
		if (this.customArgumentResolvers != null) {
			for (WebArgumentResolver argumentResolver : this.customArgumentResolvers) {
				Object value = argumentResolver.resolveArgument(methodParameter, webRequest);
				if (value != WebArgumentResolver.UNRESOLVED) {
					return value;
				}
			}
		}

		// Resolution of standard parameter types...
		Class<?> paramType = methodParameter.getParameterType();
		Object value = resolveStandardArgument(paramType, webRequest);
		if (value != WebArgumentResolver.UNRESOLVED && !ClassUtils.isAssignableValue(paramType, value)) {
			throw new IllegalStateException("Standard argument type [" + paramType.getName() +
					"] resolved to incompatible value of type [" + (value != null ? value.getClass() : null) +
					"]. Consider declaring the argument type in a less specific fashion.");
		}
		return value;
	}
	//解析标准参数：确定当前的参数是否是原生API
	protected Object resolveStandardArgument(Class<?> parameterType, NativeWebRequest webRequest) throws Exception {
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
		
		if (ServletRequest.class.isAssignableFrom(parameterType) ||							//原生API
				MultipartRequest.class.isAssignableFrom(parameterType)) {
			Object nativeRequest = webRequest.getNativeRequest(parameterType);
			if (nativeRequest == null) {
				throw new IllegalStateException(
						"Current request is not of type [" + parameterType.getName() + "]: " + request);
			}
			return nativeRequest;
		}
		else if (ServletResponse.class.isAssignableFrom(parameterType)) {							//原生API
			this.responseArgumentUsed = true;
			Object nativeResponse = webRequest.getNativeResponse(parameterType);
			if (nativeResponse == null) {
				throw new IllegalStateException(
						"Current response is not of type [" + parameterType.getName() + "]: " + response);
			}
			return nativeResponse;
		}
		else if (HttpSession.class.isAssignableFrom(parameterType)) {							//原生API
			return request.getSession();
		}
		else if (Principal.class.isAssignableFrom(parameterType)) {							//原生API
			return request.getUserPrincipal();
		}
		else if (Locale.class.equals(parameterType)) {							//原生API
			return RequestContextUtils.getLocale(request);
		}
		else if (InputStream.class.isAssignableFrom(parameterType)) {							//原生API
			return request.getInputStream();
		}
		else if (Reader.class.isAssignableFrom(parameterType)) {							//原生API
			return request.getReader();
		}
		else if (OutputStream.class.isAssignableFrom(parameterType)) {							//原生API
			this.responseArgumentUsed = true;
			return response.getOutputStream();
		}
		else if (Writer.class.isAssignableFrom(parameterType)) {							//原生API
			this.responseArgumentUsed = true;
			return response.getWriter();
		}
		return super.resolveStandardArgument(parameterType, webRequest);
	}
	
	//确定自定义类型参数的值
	private WebDataBinder resolveModelAttribute(String attrName, MethodParameter methodParam,
			ExtendedModelMap implicitModel, NativeWebRequest webRequest, Object handler) throws Exception {

		// Bind request parameter onto object...
		String name = attrName;
		//如果attrName是空串就将参数的类型首字母小写作为name
		if ("".equals(name)) {
			name = Conventions.getVariableNameForParameter(methodParam);
		}
		Class<?> paramType = methodParam.getParameterType();
		//目标对象
		Object bindObject;
		//确定目标对象的值(SpringMVC确定POJO值的三步)
		if (implicitModel.containsKey(name)) {
			//隐含模型中有值
			bindObject = implicitModel.get(name);
		}
		else if (this.methodResolver.isSessionAttribute(name, paramType)) {
			//@SessionAttribute标注，从sessionAttribute中拿
			bindObject = this.sessionAttributeStore.retrieveAttribute(webRequest, name);
			if (bindObject == null) {		//在这里可能会抛出异常，
												//目标对象被@SessionAttribute标注却在Session中找不到时会报错。所以不建议使用
				raiseSessionRequiredException("Session attribute '" + name + "' required - not found in session");
			}
		}
		else {//都不是，利用反射创建对象
			bindObject = BeanUtils.instantiateClass(paramType);
		}
		WebDataBinder binder = createBinder(webRequest, bindObject, name);
		initBinder(handler, name, binder, webRequest);
		return binder;
	}