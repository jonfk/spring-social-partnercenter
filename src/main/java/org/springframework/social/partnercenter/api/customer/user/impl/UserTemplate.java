package org.springframework.social.partnercenter.api.customer.user.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.customer.User;
import org.springframework.social.partnercenter.api.customer.request.CreateUserRequest;
import org.springframework.social.partnercenter.api.customer.request.UpdateUserPasswordRequest;
import org.springframework.social.partnercenter.api.customer.response.GetRoleListResponse;
import org.springframework.social.partnercenter.api.PagingResourceTemplate;
import org.springframework.social.partnercenter.api.customer.user.UserOperations;
import org.springframework.social.partnercenter.http.client.RestResource;

public class UserTemplate extends PagingResourceTemplate<User> implements UserOperations{
	private RestResource restResource;

	public UserTemplate(RestResource restResource, boolean isAuthorized) {
		super(restResource, isAuthorized);
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<User> createUser(String customerTenantId, CreateUserRequest request) {
		return restResource.request()
				.pathSegment(customerTenantId, "users")
				.post(request, User.class);
	}

	@Override
	public ResponseEntity<User> createUser(String customerTenantId, CreateUserRequest request, String userId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", userId)
				.post(request, User.class);
	}

	@Override
	public ResponseEntity deleteUser(String customerTenantId, String userId) {
		return restResource.request().pathSegment(customerTenantId, "users", userId).delete();
	}

	@Override
	public ResponseEntity<User> getUser(String customerTenantId, String userId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", userId)
				.get(User.class);
	}

	@Override
	public ResponseEntity<User> updateUserPassword(String customerTenantId, String userId, UpdateUserPasswordRequest request) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", userId)
				.post(request, User.class);
	}

	@Override
	public ResponseEntity<GetRoleListResponse> getUserRoles(String customerTenantId, String userId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", userId, "directoryroles")
				.get(GetRoleListResponse.class);
	}

	@Override
	public ResponseEntity<GetRoleListResponse> getAllRoles(String customerTenantId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", "directoryroles")
				.get(GetRoleListResponse.class);
	}

	@Override
	public ResponseEntity<GetRoleListResponse> getRolesByRoleId(String customerTenantId, String roleId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", roleId, "directoryroles")
				.get(GetRoleListResponse.class);
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}
}
