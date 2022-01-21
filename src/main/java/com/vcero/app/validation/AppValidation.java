package com.vcero.app.validation;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;

import com.vcero.app.exception.BadRequestException;
import com.vcero.app.exception.BadRequestMapException;
import com.vcero.app.type.TypeEntity;
import com.vcero.app.type.TypeError;
import com.vcero.entity.EntityStatus;

public class AppValidation {

	public static void exIfRequestIsNull(Object request) {
		if (Objects.isNull(request)) {
			throw new BadRequestException(TypeError.ERR_REQUEST_BODY_NULL.msg());
		}
	}

	public static void exIfRequestModelHasErrors(Object model, BindingResult result) {
		exIfRequestIsNull(model);
		if (result.hasErrors()) {
			throw new BadRequestMapException(TypeError.ERR_BEAN_VALIDATION.msg(), result);
		}
	}

//	public static void exIfRequestModelHasErrorsAndAnnotation(Object model, BindingResult result) {
//		exIfRequestModelHasErrors(model, result);
//		AppModelAnnotation.trimAndUpperCase(model);
//	}

	public static void exIfStatusAreEqual(EntityStatus entity, Boolean status) {
		if (Objects.equals(entity.getStatus(), status)) {
			throw new BadRequestException(TypeError.ERR_ENTITY_STATUS_ARE_EQUALS.msg());
		}
	}

	public static List<Integer> repeatedValuesInt(List<Integer> list) {
		Set<Integer> items = new HashSet<>();
		return list.stream().filter(n -> !items.add(n)).collect(Collectors.toList());
	}

	public static void exIfContainsRepeatedValuesInt(TypeEntity entity, List<Integer> list) {
		List<Integer> repeted = repeatedValuesInt(list);
		if (repeted.size() > 0) {
			throw new BadRequestException(entity.repeatedInt(repeted));
		}
	}

	public static void exIfContainsRepeatedValuesString(TypeEntity entity, List<String> list) {
		Set<String> items = new HashSet<>();
		List<String> repeted = list.stream().filter(n -> !items.add(n)).collect(Collectors.toList());
		if (repeted.size() > 0) {
			throw new BadRequestException(entity.repeatedString(repeted));
		}
	}

}
