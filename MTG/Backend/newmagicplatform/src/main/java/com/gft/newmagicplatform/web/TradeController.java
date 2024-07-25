// package com.gft.newmagicplatform.web;

// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.HashSet;
// import java.util.List;
// import java.util.Set;

// import org.hibernate.Hibernate;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.RequestMapping;

// import com.gft.newmagicplatform.entity.Account;
// import com.gft.newmagicplatform.io.JsonDataLoader;
// import com.gft.newmagicplatform.pojo.Card;
// import com.gft.newmagicplatform.service.AccountService;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpSession;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;

// @Controller
// @RequestMapping("/trade")
// public class TradeController {

//     private JsonDataLoader jsonDataLoader = null;
//     private AccountService accountServiceImpl;

//     public TradeController(AccountService accountService) {
//         accountServiceImpl = accountService;
//         try {
//             jsonDataLoader = new JsonDataLoader("c:/Dev/test_files/oracle-cards.json");
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     @GetMapping("/")
//     public String getTradePage(HttpServletRequest request, Model model) {

//         HttpSession session = request.getSession(false);

//         if (session != null && session.getAttribute("user") != null) {

//             Account user = (Account) session.getAttribute("user");
//             Set<Card> userCards = new HashSet<>();
//             Hibernate.initialize(user.getCards());

//             for (String card : user.getCards()) {
//                 Card cardFound = jsonDataLoader.findCardByIdParrallel(card);
//                 userCards.add(cardFound);
//             }

//             List<Account> allUsersWithoutCurrentUser = new ArrayList<>();
//             for (Account account : accountServiceImpl.getAccounts()) {
//                 if (!account.getUsername().equals(user.getUsername())) {
//                     allUsersWithoutCurrentUser.add(account);
//                 }
//             }

//             model.addAttribute("allUsers", allUsersWithoutCurrentUser);
//             model.addAttribute("cards", userCards);
//             model.addAttribute("user", user);

//             return "trade";
//         }
//         return "redirect:/startup/";
//     }

//     @GetMapping("/{userId}/cards")
//     public ResponseEntity<Set<Card>> getUserCards(@PathVariable Long userId) {
//         Account user = accountServiceImpl.getAccountById(userId);
//         Set<Card> userCards = new HashSet<>();

//         if (user != null) {
//             for (String cardId : user.getCards()) {
//                 Card card = jsonDataLoader.findCardByIdParrallel(cardId);
//                 userCards.add(card);
//             }

//             return new ResponseEntity<>(userCards, HttpStatus.OK);
//         }

//         return new ResponseEntity<>(HttpStatus.NOT_FOUND);

//     }

// }
