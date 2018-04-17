package io.devchaos.player.service.web;

import io.devchaos.player.service.domain.Player;
import io.devchaos.player.service.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author Paulo Jesus
 */
@RestController
@AllArgsConstructor
public class PlayerController {
    private PlayerService playerService;

    @GetMapping("/players")
    public Flux<Player> all(@RequestParam("active") Optional<Boolean> active) {
        return playerService.getAll(active);
    }

    @PostMapping("/players")
    public Mono<Player> create(@Validated(Player.ValidateOnCreate.class) @RequestBody Player player) {
        return playerService.create(player);
    }

    @GetMapping("/players/{id}")
    public Mono<ResponseEntity<Player>> player(@PathVariable(value = "id") String id) {
        return playerService.getPlayer(id)
                .map(it -> ResponseEntity.ok(it))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/players/{id}")
    public Mono<ResponseEntity<Player>> update(@PathVariable(value = "id") String id,
                                               @Validated(Player.ValidateOnUpdate.class) @RequestBody Player player) {
        return playerService.update(id, player)
                .map(updatedTweet -> new ResponseEntity<>(updatedTweet, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/players/{id}")
    public Mono<ResponseEntity<Void>> disable(@PathVariable(value = "id") String id) {
        return playerService.disable(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "players/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Player> stream(@RequestParam("active") Optional<Boolean> active) {
        return playerService.getAll(active);
    }
}
