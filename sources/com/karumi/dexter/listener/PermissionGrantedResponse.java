package com.karumi.dexter.listener;

public final class PermissionGrantedResponse {
    private final PermissionRequest requestedPermission;

    public PermissionGrantedResponse(PermissionRequest permissionRequest) {
        this.requestedPermission = permissionRequest;
    }

    public static PermissionGrantedResponse from(String str) {
        return new PermissionGrantedResponse(new PermissionRequest(str));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PermissionGrantedResponse.class != obj.getClass()) {
            return false;
        }
        return this.requestedPermission.getName().equals(((PermissionGrantedResponse) obj).requestedPermission.getName());
    }

    public String getPermissionName() {
        return this.requestedPermission.getName();
    }

    public PermissionRequest getRequestedPermission() {
        return this.requestedPermission;
    }

    public int hashCode() {
        return this.requestedPermission.getName().hashCode();
    }
}
