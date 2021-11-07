package pl.trosko.credit.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.trosko.credit.dto.CreditDto;
import pl.trosko.credit.service.CreditService;

import java.util.List;

@RestController
@RequestMapping("api/v1/credit")
@RequiredArgsConstructor
@Slf4j
public class CreditController {

    private final CreditService creditService;

    @GetMapping
    public ResponseEntity<List<CreditDto>> getCredit() {
        log.info("GET: Get all credits");

        return ResponseEntity.ok(creditService.getAllCredits());
    }

    @PostMapping
    public ResponseEntity<Long> createCredit(@RequestBody CreditDto credit) {
        log.info("POST: Create credit: {}", credit);

        return ResponseEntity.ok(creditService.createCredit(credit));
    }
}
