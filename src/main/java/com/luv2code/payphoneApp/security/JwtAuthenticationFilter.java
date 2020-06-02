package com.luv2code.payphoneApp.security;

import com.luv2code.payphoneApp.model.User;
import com.luv2code.payphoneApp.service.CustomeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static com.luv2code.payphoneApp.security.SecurityConstants.HEADER_STRING;
import static com.luv2code.payphoneApp.security.SecurityConstants.TOKEN_PREFIX;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    private CustomeUserService customeUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        try{

            String AccesToken = getJWTTOKENFromRequest(httpServletRequest);
            //Revalidate the token here too cos, getJWTTOKENFromRequest can return null

            if(StringUtils.hasText(AccesToken) && jwtTokenProvider.validateToken(AccesToken)){
                Long userId = jwtTokenProvider.getUserIdFromJWT(AccesToken);

                User userDetails = customeUserService.loadUserById(userId);

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                        new UsernamePasswordAuthenticationToken(userDetails,null, Collections.emptyList());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }catch (Exception e){
            logger.error("Could not set the user authentication in Security Context",e);
        }
      filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


    private String getJWTTOKENFromRequest( HttpServletRequest request){

        String bearerToken = request.getHeader(HEADER_STRING);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)){
            return bearerToken.substring(7, bearerToken.length());
        }
        return  null;
    }
}
