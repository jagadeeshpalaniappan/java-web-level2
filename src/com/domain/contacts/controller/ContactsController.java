package com.domain.contacts.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.domain.contacts.constants.ContactsConstants;
import com.domain.contacts.service.IContactsService;
import com.domain.contacts.util.JGenericUtil;
import com.domain.contacts.util.JWebUtil;
import com.domain.contacts.vo.ContactVO;
import com.domain.contacts.vo.GenericMessageVO;

public class ContactsController extends MultiActionController {

	private IContactsService contactsService = null;

	public void setContactsService(IContactsService contactsService) {
		this.contactsService = contactsService;
	}

	public ModelAndView getAllContacts(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("--------------------------START: getAllContacts()-------------------------");

		// Declaration:
		ModelAndView modelAndView = new ModelAndView(ContactsConstants.URL_GET_ALL_CONTACTS);
		List<ContactVO> contactVOs = null;

		try {

			//Data Population from HttpServletRequest

			contactVOs = contactsService.getAllContacts();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		}

		modelAndView.addObject("contactVOs", contactVOs);
		// or
		// request.setAttribute("contactVOs", contactVOs);

		logger.info("--------------------------END: getAllContacts()-------------------------");
		return modelAndView;
	}

	public ModelAndView getContact(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("--------------------------START: getContact()-------------------------");

		// Declaration:
		ModelAndView modelAndView = new ModelAndView("createViewEditContact");
		ContactVO contactVO = null;

		try {
			
			/************************************** request --to-- beanVO **************************************/
			
			//## TYPE-1
			//Using Spring MultiActionController::bind()
			//
			/*Binding the Object request --> usersVO */
			//bind(request, contactVO);
			
			
			//## TYPE-2
			//Using Apache BeanUtils.populate() 
			//
			//T object = UsersVO.class.newInstance();
			//BeanUtils.populate(object, request.getParameterMap());
			//
			/* Populating the request values into accountVO */
			
			contactVO = JWebUtil.populate(ContactVO.class, request);
			if(request.getAttribute(ContactsConstants.CONTACTS_TD)!=null){
				Long contactId = (Long) request.getAttribute(ContactsConstants.CONTACTS_TD);
				contactVO.setContactId(contactId);
			}
			
			/************************************** /request --to-- beanVO **************************************/
			
			if(JGenericUtil.isNotNull(contactVO) && JGenericUtil.isNotNull(contactVO.getContactId())){
				//Existing Contact
				contactVO = contactsService.getContact(contactVO);
			}else{
				//New Contact
			}

			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		}

		modelAndView.addObject("contactVO", contactVO);
		// or
		// request.setAttribute("contactVOs", contactVOs);
		logger.info("--------------------------END: getContact()-------------------------");
		return modelAndView;
	}

	public ModelAndView saveContact(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("--------------------------START: getContact()-------------------------");

		// Declaration:
		ModelAndView modelAndView = new ModelAndView("forward:getContact.htm");
		ContactVO contactVO = null;
		GenericMessageVO genericMessageVO = null;
		/* Populating the request values into accountVO */
		contactVO = JWebUtil.populate(ContactVO.class, request);
		
		try {

			genericMessageVO = contactsService.saveContact(contactVO);
			
			if (genericMessageVO.getCode().equalsIgnoreCase(ContactsConstants.SUCCESS)) {
				request.setAttribute(ContactsConstants.DISPLAY_SUCCESS_MESSAGE, JWebUtil.getMessage(ContactsConstants.MSG_SAVE_CONTACT_SUCCESS, request));

			} else if (genericMessageVO.getCode().equalsIgnoreCase(ContactsConstants.VALIDATION_FAILURE)) {
				request.setAttribute(ContactsConstants.DISPLAY_VALIDATION_MESSAGE, JWebUtil.getMessage("", request));
				
				
			} else if (genericMessageVO.getCode().equalsIgnoreCase(ContactsConstants.TECHNICAL_FAILURE)) {
				request.setAttribute(ContactsConstants.DISPLAY_FAILURE_MESSAGE, JWebUtil.getMessage(ContactsConstants.MSG_SAVE_CONTACT_TECHNICAL_FAILURE, request));

			} else {
				// FAILURE
				request.setAttribute(ContactsConstants.DISPLAY_FAILURE_MESSAGE, JWebUtil.getMessage(ContactsConstants.MSG_SAVE_CONTACT_FAILURE, request));
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			request.setAttribute(ContactsConstants.DISPLAY_FAILURE_MESSAGE, JWebUtil.getMessage(ContactsConstants.MSG_SAVE_CONTACT_TECHNICAL_FAILURE, request));

		}

		//modelAndView.addObject("contactVO", contactVO);
		// or
		request.setAttribute("contactVO", contactVO);
		request.setAttribute("contactId", contactVO.getContactId());
		logger.info("--------------------------END: getContact()-------------------------");
		return modelAndView;
	}

	public ModelAndView deleteContact(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("--------------------------START: deleteContact()-------------------------");

		// Declaration:
		ModelAndView modelAndView = new ModelAndView(ContactsConstants.URL_FWD_GET_ALL_CONTACTS);
		ContactVO contactVO = null;
		GenericMessageVO genericMessageVO = null;

		try {
			contactVO = JWebUtil.populate(ContactVO.class, request);
			
			genericMessageVO = contactsService.deleteContact(contactVO);

			if (genericMessageVO.getCode().equalsIgnoreCase(ContactsConstants.SUCCESS)) {

			} else if (genericMessageVO.getCode().equalsIgnoreCase(ContactsConstants.VALIDATION_FAILURE)) {

			} else if (genericMessageVO.getCode().equalsIgnoreCase(ContactsConstants.TECHNICAL_FAILURE)) {

			} else {
				// FAILURE
			}

			modelAndView.addObject("contactVO", contactVO);
			// or
			// request.setAttribute("contactVO", contactVO);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		}
		logger.info("--------------------------END: deleteContact()-------------------------");
		return modelAndView;
	}
}
