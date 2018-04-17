package io.devchaos.player.search.service.web;

import io.devchaos.player.search.service.domain.player.PlayerDocument;
import io.devchaos.player.search.service.domain.search.InvalidSearchParamException;
import io.devchaos.player.search.service.service.PlayerSearchService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class PlayerSearchController {

    private PlayerSearchService playerSearchService;

    @GetMapping("/search")
    public List<PlayerDocument> search(@RequestParam MultiValueMap<String, String> parameters) throws InvalidSearchParamException {
        return playerSearchService.search(parameters);
    }

    @ExceptionHandler({InvalidSearchParamException.class})
    public ResponseEntity handleWrongRequest(HttpServletRequest req, Exception ex) {
        log.error("Invalid request '{}' parameters", req.getQueryString(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity handleException(HttpServletRequest req, Exception ex) {
        log.error("Request error '{}'", req.getQueryString(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }


}
