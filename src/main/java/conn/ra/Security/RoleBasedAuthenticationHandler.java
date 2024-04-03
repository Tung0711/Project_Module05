package conn.ra.Security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;

public class RoleBasedAuthenticationHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl ( authentication );
        response.sendRedirect ( targetUrl );
    }

    private String determineTargetUrl(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities ();
        if (authorities.contains ( new SimpleGrantedAuthority ( "ROLE_USER" ) )) {
            return "index.html";
        } else if (authorities.contains ( new SimpleGrantedAuthority ( "ROLE_ADMIN" ) )) {
            return "admin/admin-dashboard.html";
        } else {
            throw new IllegalStateException ( "Unexpected user role" );
        }
    }
}