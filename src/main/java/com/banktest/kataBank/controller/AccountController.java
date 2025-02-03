package com.banktest.kataBank.controller;

import com.banktest.kataBank.domain.dto.TransactionDTO;
import com.banktest.kataBank.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * Dépôt d'argent sur le compte spécifié
     * @param id ID du compte
     * @param amount Montant à déposer
     * @return Réponse indiquant si l'opération a réussi
     */
    @Operation(summary = "Dépôt d'argent sur le compte", description = "Effectue un dépôt sur un compte bancaire spécifié.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dépôt effectué avec succès"),
            @ApiResponse(responseCode = "400", description = "Montant invalide ou compte non trouvé")
    })
    @PostMapping("/{id}/deposit/{amount}")
    public ResponseEntity<String> deposit(
            @Parameter(description = "ID du compte bancaire") @PathVariable Long id,
            @Parameter(description = "Montant à déposer") @PathVariable double amount) {
        accountService.deposit(id, amount);
        return ResponseEntity.ok("Dépôt réussi");
    }

    /**
     * Retrait d'argent sur le compte spécifié
     * @param id ID du compte
     * @param amount Montant à retirer
     * @return Réponse indiquant si l'opération a réussi
     */
    @Operation(summary = "Retrait d'argent du compte", description = "Effectue un retrait d'argent sur un compte bancaire spécifié.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrait effectué avec succès"),
            @ApiResponse(responseCode = "400", description = "Fonds insuffisants ou compte non trouvé")
    })
    @PostMapping("/{id}/withdraw/{amount}")
    public ResponseEntity<String> withdraw(
            @Parameter(description = "ID du compte bancaire") @PathVariable Long id,
            @Parameter(description = "Montant à retirer") @PathVariable double amount) {
        accountService.withdraw(id, amount);
        return ResponseEntity.ok("Retrait réussi");
    }

    /**
     * Récupère le solde du compte spécifié
     * @param id ID du compte
     * @return Le solde du compte
     */
    @Operation(summary = "Obtenir le solde du compte", description = "Retourne le solde actuel du compte bancaire spécifié.")
    @ApiResponse(responseCode = "200", description = "Solde récupéré avec succès")
    @GetMapping("/{id}/balance")
    public ResponseEntity<Double> getBalance(
            @Parameter(description = "ID du compte bancaire") @PathVariable Long id) {
        return ResponseEntity.ok(accountService.getBalance(id));
    }

    /**
     * Récupère toutes les transactions du compte spécifié
     * @param id ID du compte
     * @return Liste des transactions associées au compte
     */
    @Operation(summary = "Obtenir les transactions du compte", description = "Retourne une liste de toutes les transactions effectuées sur le compte spécifié.")
    @ApiResponse(responseCode = "200", description = "Transactions récupérées avec succès")
    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionDTO>> getTransactions(
            @Parameter(description = "ID du compte bancaire") @PathVariable Long id) {
        List<TransactionDTO> transactions = accountService.getTransactions(id)
                .stream()
                .map(t -> new TransactionDTO(t.getType().name(), t.getAmount(), t.getTimestamp()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(transactions);
    }
}
