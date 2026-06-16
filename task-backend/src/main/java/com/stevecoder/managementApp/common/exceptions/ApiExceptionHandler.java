package com.stevecoder.managementApp.common.exceptions;

import com.stevecoder.managementApp.task.domain.exception.TaskNotFoundException;
import com.stevecoder.managementApp.user.domain.exception.UserAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorMessage badRequest(HttpServletRequest request, MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return new ErrorMessage(exception.getMessage(), exception.getClass().getSimpleName(), request.getRequestURI(), errors);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseBody
    public ErrorMessage taskNotFound(HttpServletRequest request, TaskNotFoundException exception) {
        return new ErrorMessage(exception.getMessage(), exception.getClass().getSimpleName(), request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseBody
    public ErrorMessage userAlreadyExists(HttpServletRequest request, UserAlreadyExistsException exception) {
        return new ErrorMessage(exception.getMessage(), exception.getClass().getSimpleName(), request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    public ErrorMessage badCredentials(HttpServletRequest request, BadCredentialsException exception) {
        return new ErrorMessage("Invalid username or password", exception.getClass().getSimpleName(), request.getRequestURI());
    }
}
