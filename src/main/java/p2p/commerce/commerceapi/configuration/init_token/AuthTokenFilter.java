package p2p.commerce.commerceapi.configuration.init_token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import p2p.commerce.commerceapi.configuration.data.UserDetailsServiceImpl;
import p2p.commerce.commerceapi.web.model.UserAuthenticationLog;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getTokenHeader(request);
            if (token != null) {
                boolean isTokenValid = tokenProvider.validateToken(token);
                if (isTokenValid) {
                    UserAuthenticationLog tokens = tokenProvider.getDataToken(token);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(tokens.getUser().getUsername());
                    UsernamePasswordAuthenticationToken authentication
                            = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch  (Exception e) {
            System.out.println("Can NOT set user authentication -> Message: " + e.getMessage());
        } finally {
            filterChain.doFilter(request, response);
        }
    }
    private String getTokenHeader(HttpServletRequest request) {
        String token = request.getHeader("x-token-id");
        if (token != null) {
            return token;
        }
        return null;
    }
}
