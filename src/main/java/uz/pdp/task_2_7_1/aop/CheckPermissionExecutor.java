package uz.pdp.task_2_7_1.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.task_2_7_1.entity.User;
import uz.pdp.task_2_7_1.exception.ForbiddenException;

@Component
@Aspect
public class CheckPermissionExecutor {
    @Before(value = "@annotation(checkPermission)")
    public void checkUserPermissionMyMethod(CheckPermission checkPermission){

        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean exists = false;
        for (GrantedAuthority authority : user.getAuthorities()) {
            if(authority.getAuthority().equals(checkPermission.value())){
                exists = true;
                break;
            }
        }
        if(!exists){
            throw new ForbiddenException("Not authorized");
        }
    }
}
