/**SpringMVC视图解析原理	viewResolver SpringMVC 九大组件之一
  *1.方法执行后的返回值会作为页面地址参考，转发或者重定向到其他页面
  *2.视图解析器可能会进行页面地址拼串*/
  
 /*视图解析器只是得到一个视图对象，视图对象才能真正的转发(将隐含模型中的数据放在请求域中)或者重定向到页面才能真正的渲染视图*/
 /**视图的作用是渲染模型数据(View接口中的render接口)，将模型里的数据以某种形式呈现给客户，视图由视图解析器负责实例化。 */
 /**视图是无状态的，不会有线程安全问题*/
 /**无状态：*/
 
 /**视图解析器通过方法返回值得到视图对象
	视图对象不同就可以具有不同的功能*/
	|| 
  运||	执行目标方法返回ModelAndView对象，任何方法的返回值都会包装成ModelAndView对象
	||	mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
  行|| 
	||	来到页面的方法，数据渲染(展现数据)
  流||	processDispatchResult(processedRequest, response, mappedHandler, mv, dispatchException);
	||  				||
  程||			渲染页面\/
	||			render(mv, request, response);
	||					||
	||					\/View与ViewResolver：ViewResolver的作用是根据视图名(方法的返回值)的带View对象；
	\/				view = resolveViewName(mv.getViewName(), mv.getModelInternal(), locale, request);
							||
							\/		//根据方法的返回值创建出View对象
						view = createView(viewName, locale);
							||
							\/		//view对象调用render方法开始渲染
						view.render(mv.getModelInternal(), request, response);
	
	
	
	
	protected View resolveViewName(String viewName, Map<String, Object> model, Locale locale,
			HttpServletRequest request) throws Exception {

		//遍历所有的viewResolver，我们在配置文件中配置或者使用默认的
		//所有配置的视图解析器都会来尝试根据视图名(返回值)得到view对象
		for (ViewResolver viewResolver : this.viewResolvers) {
			//视图解析器得到一个view对象
			View view = viewResolver.resolveViewName(viewName, locale);
			if (view != null) {
				return view;
			}
		}
		return null;
	}
	
	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		if (!isCache()) {
			return createView(viewName, locale);
		}
		else {
			Object cacheKey = getCacheKey(viewName, locale);
			View view = this.viewAccessCache.get(cacheKey);
			if (view == null) {
				synchronized (this.viewCreationCache) {
					view = this.viewCreationCache.get(cacheKey);
					if (view == null) {
						// Ask the subclass to create the View object.
						//根据方法的返回值创建出View对象
						view = createView(viewName, locale);
						if (view == null && this.cacheUnresolved) {
							view = UNRESOLVED_VIEW;
						}
						if (view != null) {
							this.viewAccessCache.put(cacheKey, view);
							this.viewCreationCache.put(cacheKey, view);
							if (logger.isTraceEnabled()) {
								logger.trace("Cached view [" + cacheKey + "]");
							}
						}
					}
				}
			}
			return (view != UNRESOLVED_VIEW ? view : null);
		}
	}
	
	//根据视图名创建View对象
	@Override
	protected View createView(String viewName, Locale locale) throws Exception {
		// If this resolver is not supposed to handle the given view,
		// return null to pass on to the next resolver in the chain.
		if (!canHandle(viewName, locale)) {
			return null;
		}
		// Check for special "redirect:" prefix.
		if (viewName.startsWith(REDIRECT_URL_PREFIX)) {
			String redirectUrl = viewName.substring(REDIRECT_URL_PREFIX.length());
			RedirectView view = new RedirectView(redirectUrl, isRedirectContextRelative(), isRedirectHttp10Compatible());
			return applyLifecycleMethods(viewName, view);
		}
		// Check for special "forward:" prefix.
		if (viewName.startsWith(FORWARD_URL_PREFIX)) {
			String forwardUrl = viewName.substring(FORWARD_URL_PREFIX.length());
			return new InternalResourceView(forwardUrl);
		}
		// Else fall back to superclass implementation: calling loadView.
		//如果没有前缀使用父类创建一个View对象，在这里会进行拼串
		return super.createView(viewName, locale);
	}
	
	//View调用的render方法
	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (logger.isTraceEnabled()) {
			logger.trace("Rendering view with name '" + this.beanName + "' with model " + model +
				" and static attributes " + this.staticAttributes);
		}

		Map<String, Object> mergedModel = createMergedOutputModel(model, request, response);

		prepareResponse(request, response);
		
		//渲染要给对面输出的所有数据
		renderMergedOutputModel(mergedModel, request, response);
	}
	//InternalResourceView中的renderMergedOutputModel方法
	@Override
	protected void renderMergedOutputModel(
			Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Determine which request handle to expose to the RequestDispatcher.
		HttpServletRequest requestToExpose = getRequestToExpose(request);

		// Expose the model object as request attributes.
		exposeModelAsRequestAttributes(model, requestToExpose);	//隐含模型中的数据放在请求域中

		// Expose helpers as request attributes, if any.
		exposeHelpers(requestToExpose);

		// Determine the path for the request dispatcher.拿到转发或者重定向的路径
		String dispatcherPath = prepareForRendering(requestToExpose, response);

		// Obtain a RequestDispatcher for the target resource (typically a JSP).拿到servlet中规定的转发器
		RequestDispatcher rd = getRequestDispatcher(requestToExpose, dispatcherPath);
		if (rd == null) {
			throw new ServletException("Could not get RequestDispatcher for [" + getUrl() +
					"]: Check that the corresponding file exists within your web application archive!");
		}

		// If already included or response already committed, perform include, else forward.
		if (useInclude(requestToExpose, response)) {
			response.setContentType(getContentType());
			if (logger.isDebugEnabled()) {
				logger.debug("Including resource [" + getUrl() + "] in InternalResourceView '" + getBeanName() + "'");
			}
			rd.include(requestToExpose, response);
		}

		else {
			// Note: The forwarded resource is supposed to determine the content type itself.
			if (logger.isDebugEnabled()) {
				logger.debug("Forwarding to resource [" + getUrl() + "] in InternalResourceView '" + getBeanName() + "'");
			}
			//转发操作
			rd.forward(requestToExpose, response);
		}
	}
	//隐含模型中的数据放在请求域中
	protected void exposeModelAsRequestAttributes(Map<String, Object> model, HttpServletRequest request) throws Exception {
		for (Map.Entry<String, Object> entry : model.entrySet()) {
			String modelName = entry.getKey();
			Object modelValue = entry.getValue();
			if (modelValue != null) {
				request.setAttribute(modelName, modelValue);
				if (logger.isDebugEnabled()) {
					logger.debug("Added model object '" + modelName + "' of type [" + modelValue.getClass().getName() +
							"] to request in view with name '" + getBeanName() + "'");
				}
			}
			else {
				request.removeAttribute(modelName);
				if (logger.isDebugEnabled()) {
					logger.debug("Removed model object '" + modelName +
							"' from request in view with name '" + getBeanName() + "'");
				}
			}
		}
	}