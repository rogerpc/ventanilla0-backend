package com.vcero.repository.builder;

import java.lang.reflect.InvocationTargetException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vcero.app.exception.BadRequestException;
import com.vcero.app.exception.NotFoundException;
import com.vcero.app.response.AppResponse;
import com.vcero.app.response.HttpOk;
import com.vcero.app.type.TypeEntity;
import com.vcero.app.type.TypeError;
import com.vcero.app.type.TypeErrorArgs;
import com.vcero.app.validation.AppValidation;
import com.vcero.entity.EntityStatus;
import com.vcero.entity.EntityType;

@Repository
public class QueryBuilder {

	@PersistenceContext
	private EntityManager entityManager;

	private <T extends EntityType> TypeEntity getTypeEntity(Class<T> entity) {
		try {
			return entity.getDeclaredConstructor().newInstance().type();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}

	private <T extends EntityStatus> T entityStatusIsTrue(T entity) {
		if (!entity.getStatus()) {
			throw new BadRequestException(entity.type().status(false, entity.id()));
		}
		return entity;
	}

//	private String listToSqlArray(List<Integer> array) {
//		return "{" + StringUtils.join(array, ",") + "}";
//	}

	private void checkInstanceOfKey(Object key) {
		if (key instanceof String) {
		} else if (key instanceof Integer) {
		} else {
			throw new RuntimeException(TypeError.ERR_INSTANCE_OF_KEY.msg());
		}
	}

	/*
	 * USUARIO
	 */

//	public Integer getIdempleadoPrincipal() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication instanceof AnonymousAuthenticationToken) {
//			throw new UnauthorizedException(); // anonymousUser
//		}
//		Usuario user = this.findByIdAndStatusTrueEx404(Usuario.class, authentication.getName());
//		return user.getIdempleado();
//	}

	/*
	 * ENTITY 404 (NOT FOUND)
	 */

	public <T extends EntityType> T findByIdEx404(Class<T> cls, Object id) {
		checkInstanceOfKey(id);
		T entity = entityManager.find(cls, id);
		if (Objects.isNull(entity)) {
			throw new NotFoundException(getTypeEntity(cls), id.toString());
		}
		return entity;
	}

	public <T extends EntityStatus> T findByIdAndStatusTrueEx404(Class<T> cls, Object id) {
		T entity = findByIdEx404(cls, id);
		return entityStatusIsTrue(entity);
	}

	public <T> T genericFindByIdEx404(Class<T> cls, Object id, TypeEntity type) {
		checkInstanceOfKey(id);
		T entity = entityManager.find(cls, id);
		if (Objects.isNull(entity)) {
			throw new NotFoundException(type, id.toString());
		}
		return entity;
	}

	/*
	 * ENTITY 400 (BAD REQUEST)
	 */

	public <T extends EntityType> T findByIdEx400(Class<T> cls, Object id) {
		checkInstanceOfKey(id);
		T entity = entityManager.find(cls, id);
		if (Objects.isNull(entity)) {
			throw new BadRequestException(getTypeEntity(cls).notFound(id.toString()));
		}
		return entity;
	}

	public <T extends EntityStatus> T findByIdAndStatusTrueEx400(Class<T> cls, Object id) {
		T entity = findByIdEx400(cls, id);
		return entityStatusIsTrue(entity);
	}

	/*
	 * ENTITY UP
	 */

	public <T extends EntityStatus> void upExistsByIdAndStatusTrueEx400(Class<T> cls, Integer id) {
		T entity = findByIdEx400(cls, id);
		if (!entity.getStatus()) {
			throw new BadRequestException(entity.type().requieredStatusTrue(id.toString()));
		}
	}

	/*
	 * ENTITY 404 (NOT FOUND)
	 */

	public <T extends EntityType> T findByIdAndTypeEx404(Class<T> cls, Integer id, TypeEntity typeSearch) {
		T entity = entityManager.find(cls, id);
		if (Objects.isNull(entity)) {
			throw new NotFoundException(getTypeEntity(cls), id.toString());
		}
		if (!entity.type().equals(typeSearch)) {
			throw new BadRequestException(typeSearch.notFound(id));
		}
		return entity;
	}

	public <T extends EntityStatus> T findByIdAndTypeAndStatusTrueEx404(Class<T> cls, Integer id,
			TypeEntity typeSearch) {
		T entity = findByIdAndTypeEx404(cls, id, typeSearch);
		return entityStatusIsTrue(entity);
	}

	/*
	 * ENTITY 400 (NOT FOUND)
	 */

	public <T extends EntityType> T findByIdAndTypeEx400(Class<T> cls, Integer id, TypeEntity typeSearch) {
		T entity = entityManager.find(cls, id);
		if (Objects.isNull(entity)) {
			throw new NotFoundException(getTypeEntity(cls), id.toString());
		}
		if (!entity.type().equals(typeSearch)) {
			throw new BadRequestException(typeSearch.notFound(id));
		}
		return entity;
	}

	public <T extends EntityStatus> T findByIdAndTypeAndStatusTrueEx400(Class<T> cls, Integer id,
			TypeEntity typeSearch) {
		T entity = findByIdAndTypeEx400(cls, id, typeSearch);
		return entityStatusIsTrue(entity);
	}

	/*
	 * ENTITY BUILD FK (HAVE)
	 */

	public <T extends EntityType> void exIfHaveReferencesByIdFk(Class<T> cls, String property, Integer idFk,
			TypeEntity entityParent) {
		Query query = new Query();
		query.addFilter(property, "=", idFk.toString(), "number");
		Long count = queryCount(cls, query);
		if (count > 0) {
			throw new BadRequestException(entityParent.have(getTypeEntity(cls), count));
		}
	}

	public <T extends EntityStatus> void exIfHaveReferencesInStatusTrueByIdFK(Class<T> cls, String property,
			Integer idFk, TypeEntity entityParent) {
		Query query = new Query();
		query.addFilter(property, "=", idFk.toString(), "number");
		query.addFilter("status", "=", "true", "boolean");
		Long count = queryCount(cls, query);
		if (count > 0) {
			throw new BadRequestException(entityParent.have(getTypeEntity(cls), count, true));
		}
	}

	public <T extends EntityStatus> void exIfHaveReferencesInStatusTrueByIdFK(Class<T> cls, String mappedBy,
			String property, Integer idFk, TypeEntity entityParent) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cr = cb.createQuery(Long.class);
		Root<T> root = cr.from(cls);
		cr.select(cb.count(root));
		// Where
		Predicate predicate = cb.conjunction();
		predicate = cb.and(predicate, cb.equal(root.get(mappedBy).get(property), idFk));
		predicate = cb.and(predicate, cb.equal(root.get("status"), true));
		cr.where(predicate);
		// Result
		Long count = entityManager.createQuery(cr).getSingleResult();
		if (count > 0) {
			throw new BadRequestException(entityParent.have(getTypeEntity(cls), count, true));
		}
	}

	/*
	 * ENTITY BUILD FK (BE)
	 */

	public <T extends EntityType> void exIfBeReferencesByIdFk(Class<T> cls, String property, Object idFk,
			TypeEntity entityParent) {
		Query query = new Query();
		if (idFk instanceof String) {
			query.addFilter(property, "lk", idFk.toString(), "string");
		} else if (idFk instanceof Integer) {
			query.addFilter(property, "=", idFk.toString(), "number");
		} else {
			throw new RuntimeException(TypeError.ERR_INSTANCE_OF_KEY.msg());
		}
		Long count = queryCount(cls, query);
		if (count > 0) {
			throw new BadRequestException(entityParent.be(getTypeEntity(cls), count));
		}
	}

	public <T extends EntityStatus> void exIfBeReferencesInStatusTrueByIdFK(Class<T> cls, String property, Object idFk,
			TypeEntity entityParent) {
		Query query = new Query();
		if (idFk instanceof String) {
			query.addFilter(property, "lk", idFk.toString(), "string");
		} else if (idFk instanceof Integer) {
			query.addFilter(property, "=", idFk.toString(), "number");
		} else {
			throw new RuntimeException(TypeError.ERR_INSTANCE_OF_KEY.msg());
		}
		query.addFilter("status", "=", "true", "boolean");
		Long count = queryCount(cls, query);
		if (count > 0) {
			throw new BadRequestException(entityParent.be(getTypeEntity(cls), count, true));
		}
	}

	/*
	 * RESPONSE ENTITY (POST)
	 */

	public <T extends EntityType> ResponseEntity<HttpOk> responseEntityFind(Class<T> cls, Query query,
			BindingResult result) {
		query = Optional.ofNullable(query).orElse(new Query());
		AppValidation.exIfRequestModelHasErrors(query, result);					
		return AppResponse.ok(getTypeEntity(cls).plural(), queryDataPagination(cls, query));
	}

	/*
	 * RESPONSE ENTITY (GET)
	 */

	public <T extends EntityStatus> ResponseEntity<HttpOk> responseEntityFindById(Class<T> cls, Object id,
			Boolean status) {
		T entity = findByIdEx404(cls, id);
		if (status != null) {
			if (!entity.getStatus().equals(status)) {
				throw new BadRequestException(entity.type().status(entity.getStatus(), id.toString()));
			}
		}
		return AppResponse.ok(entity.type().singular(), entity);
	}

	public <T extends EntityStatus> ResponseEntity<HttpOk> responseEntityFindByIdAndType(Class<T> cls, Integer id,
			Boolean status, TypeEntity typeSearch) {
		T entity = findByIdAndTypeEx404(cls, id, typeSearch);
		if (status != null) {
			if (!entity.getStatus().equals(status)) {
				throw new BadRequestException(entity.type().status(entity.getStatus(), id));
			}
		}
		return AppResponse.ok(entity.type().singular(), entity);
	}

	/*
	 * BUILD
	 */

	private <T extends EntityType> QData<T> queryDataPagination(Class<T> cls, Query query) {
		Long totalItems = this.queryCount(cls, query);
		Integer itemsLastPage = (int) (totalItems % query.getLimit());
		Integer totalPages = ((int) (totalItems / query.getLimit())) + (itemsLastPage > 0 ? 1 : 0);
		Integer itemsPage = query.getLimit();

		if (query.getPage() == totalPages) {
			if (itemsLastPage > 0) {
				itemsPage = itemsLastPage;
			}
		}

		Integer fromItem = (query.getPage() - 1) * query.getLimit();
		Integer toItem = fromItem + itemsPage;

		QDataPage page = new QDataPage();
		page.setPageNumber(query.getPage());
		page.setItemsPerPage(query.getLimit());

		QData<T> data = new QData<T>();
		data.setTotalItems(totalItems);
		data.setTotalPages(totalPages);
		data.setCurrentPage(page);

		if (query.getPage() > totalPages) {
			return data;
		}

		data.getCurrentPage().setItemsPage(itemsPage);
		data.getCurrentPage().setFromItem(fromItem + 1);
		data.getCurrentPage().setToItem(toItem);
		data.setData(this.queryFind(cls, query));
		return data;
	}

	private <T extends EntityType> Long queryCount(Class<T> cls, Query query) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cr = cb.createQuery(Long.class);
		Root<T> root = cr.from(cls);
		cr.select(cb.count(root));
		// Where
		Predicate predicate = this.queryWhere(query, cb, root);
		cr.where(predicate);
		// Result
		return entityManager.createQuery(cr).getSingleResult();
	}

	private <T extends EntityType> List<T> queryFind(Class<T> cls, Query query) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cr = cb.createQuery(cls);
		Root<T> root = cr.from(cls);
		// Where
		Predicate predicate = this.queryWhere(query, cb, root);
		cr.where(predicate);
		// Order By
		List<Order> orderBy = new ArrayList<Order>();
		for (QuerySort sort : query.getSorts()) {
			switch (sort.getOrder().toLowerCase()) {
			case "asc":
				orderBy.add(cb.asc(root.get(sort.getKey())));
				break;
			case "desc":
				orderBy.add(cb.desc(root.get(sort.getKey())));
				break;
			default:
				throw new BadRequestException(TypeErrorArgs.ERR_QUERY_ORDER_BY_2x.msg(sort.getOrder(), sort.getKey()));
			}
		}
		cr.orderBy(orderBy);
		// Result
		return entityManager.createQuery(cr) //
				.setFirstResult((query.getPage() - 1) * query.getLimit()) // Offset
				.setMaxResults(query.getLimit()) // Limit
				.getResultList();
	}

	private <T extends EntityType> Predicate queryWhere(Query params, CriteriaBuilder cb, Root<T> root) {
		Predicate predicate = cb.conjunction();
		for (QueryFilter filter : params.getFilters()) {
			String type = Objects.requireNonNull(filter.getType());
			String key = Objects.requireNonNull(filter.getKey());
			String operator = Objects.requireNonNull(filter.getOperator());
			String value = Objects.requireNonNull(filter.getValue());
			System.out.println(key + " " + operator + " " + value);
			try {
				switch (type) {
				// STRING
				case "string":
					String literal = operator.replace("_", "%");
					switch (operator) {
					case "lk":
					case "lk_":
					case "_lk":
					case "_lk_":
						literal = literal.replace("lk", value);
						break;
					case "nlk":
					case "nlk_":
					case "_nlk":
					case "_nlk_":
						literal = literal.replace("nlk", value);
						break;
					default:
						throw new BadRequestException(TypeErrorArgs.ERR_QUERY_OPERATOR_3x.msg(operator, key, type));
					}
					Expression<String> keyUnaccent = cb.function("unaccent", String.class, root.get(key));
					Expression<String> keyUpper = cb.function("upper", String.class, keyUnaccent);
					Expression<String> valUnaccent = cb.function("unaccent", String.class, cb.literal(literal));
					Expression<String> valUpper = cb.function("upper", String.class, valUnaccent);
					if (operator.contains("nlk")) {
						predicate = cb.and(predicate, cb.notLike(keyUpper, valUpper));
					} else {
						predicate = cb.and(predicate, cb.like(keyUpper, valUpper));
					}
					break;
				// NUMBER
				case "number":
					if (!NumberUtils.isParsable(value)) {
						throw new BadRequestException(TypeErrorArgs.ERR_QUERY_TYPE_NUMBER_3x.msg(type, key, value));
					}
					Integer numero = Integer.parseInt(value);
					switch (operator) {
					case "=":
						predicate = cb.and(predicate, cb.equal(root.get(key), numero));
						break;
					case "<":
						predicate = cb.and(predicate, cb.lessThan(root.get(key), numero));
						break;
					case ">":
						predicate = cb.and(predicate, cb.greaterThan(root.get(key), numero));
						break;
					case "<=":
						predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get(key), numero));
						break;
					case ">=":
						predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get(key), numero));
						break;
					case "!=":
						predicate = cb.and(predicate, cb.notEqual(root.get(key), numero));
						break;
					default:
						throw new BadRequestException(TypeErrorArgs.ERR_QUERY_OPERATOR_3x.msg(operator, key, type));
					}
					break;
				// DATE
				case "date":
					ZonedDateTime fecha = ZonedDateTime.parse(value);
					if (Objects.isNull(fecha)) {
						throw new BadRequestException(TypeErrorArgs.ERR_QUERY_TYPE_DATE_3x.msg(type, key, value));
					}
					switch (operator) {
					case "=":
						predicate = cb.and(predicate, cb.equal(root.get(key), fecha));
						break;
					case "<":
						predicate = cb.and(predicate, cb.lessThan(root.get(key), fecha));
						break;
					case ">":
						predicate = cb.and(predicate, cb.greaterThan(root.get(key), fecha));
						break;
					case "<=":
						predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get(key), fecha));
						break;
					case ">=":
						predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get(key), fecha));
						break;
					case "!=":
						predicate = cb.and(predicate, cb.notEqual(root.get(key), fecha));
						break;
					default:
						throw new BadRequestException(TypeErrorArgs.ERR_QUERY_OPERATOR_3x.msg(operator, key, type));
					}
					break;
				// BOOLEAN
				case "boolean":
					switch (operator) {
					case "=":
						switch (value) {
						case "true":
						case "false":
							predicate = cb.and(predicate, cb.equal(root.get(key), Boolean.parseBoolean(value)));
							break;
						default:
							throw new BadRequestException(
									TypeErrorArgs.ERR_QUERY_TYPE_BOOLEAN_3x.msg(type, key, value));
						}
						break;
					default:
						throw new BadRequestException(TypeErrorArgs.ERR_QUERY_OPERATOR_3x.msg(operator, key, type));
					}
					break;
				// IS NULL
				case "isnull":
					switch (operator) {
					case "=":
						switch (value) {
						case "true":
							predicate = cb.and(predicate, cb.isNull(root.get(key)));
							break;
						case "false":
							predicate = cb.and(predicate, cb.isNotNull(root.get(key)));
							break;
						default:
							throw new BadRequestException(
									TypeErrorArgs.ERR_QUERY_OPERATOR_NULL_3x.msg(operator, key, value));
						}
						break;
					default:
						throw new BadRequestException(TypeErrorArgs.ERR_QUERY_OPERATOR_3x.msg(operator, key, type));
					}
					break;
				// LIST
				case "list":
					try {
						ObjectMapper mapper = new ObjectMapper();
						Object[] values = mapper.readValue(value, Object[].class);
						switch (operator) {
						case "in":
							predicate = cb.and(predicate, root.get(key).in(values));
							break;
						case "notin":
							predicate = cb.and(predicate, root.get(key).in(values).not());
							break;
						default:
							throw new BadRequestException(TypeErrorArgs.ERR_QUERY_OPERATOR_3x.msg(operator, key, type));
						}
					} catch (JsonProcessingException e) {
						throw new BadRequestException(
								TypeErrorArgs.ERR_QUERY_OPERATOR_STR_ARRAY_3x.msg(operator, key, value));
					}
					break;
				default:
					throw new BadRequestException(TypeErrorArgs.ERR_QUERY_TYPE_NOT_FOUND_x.msg(type));
				}
			}
			// NOT FOUND KEY
			catch (IllegalArgumentException e) {
				throw new BadRequestException(TypeErrorArgs.ERR_QUERY_PROPERTY_NOT_FOUND_x.msg(key));
			}

		}
		return predicate;

	}

}
