package ir.iraniancyber.khaneshyar.controller;

import ir.iraniancyber.khaneshyar.dto.ExceptionResponseDto;
import ir.iraniancyber.khaneshyar.exeption.RuleException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionController {

    private final MessageSourceAccessor messageSourceAccessor;

    public CustomExceptionController(MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @ExceptionHandler(RuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<ExceptionResponseDto>> ruleExceptionHandler(RuleException e) {
        final List<ExceptionResponseDto> exceptionResponseDtos = new ArrayList<>();
        exceptionResponseDtos.add(new ExceptionResponseDto(messageSourceAccessor.getMessage(e.getMessage())
                , e.getMessage()));
        return ResponseEntity.ok(exceptionResponseDtos);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<ExceptionResponseDto>> exceptionHandler(Exception e) {
        final List<ExceptionResponseDto> exceptionResponseDtos = new ArrayList<>();
        exceptionResponseDtos.add(new ExceptionResponseDto(
                messageSourceAccessor.getMessage("exception.error")
                , "exception.error"));
        return ResponseEntity.ok(exceptionResponseDtos);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<ExceptionResponseDto>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        final List<ExceptionResponseDto> exceptionResponseDtos = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(err ->
                exceptionResponseDtos.add(
                        new ExceptionResponseDto(messageSourceAccessor.getMessage(err.getDefaultMessage())
                        , err.getDefaultMessage()))
        );
        return ResponseEntity.ok(exceptionResponseDtos);
    }
}

