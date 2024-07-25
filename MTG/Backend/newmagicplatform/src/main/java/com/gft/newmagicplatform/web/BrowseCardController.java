// package com.gft.newmagicplatform.web;

// import java.io.IOException;
// import java.util.List;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.servlet.ModelAndView;

// import com.gft.newmagicplatform.io.JsonDataLoader;
// import com.gft.newmagicplatform.pojo.Card;
// import com.gft.newmagicplatform.service.AccountService;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpSession;

// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;

// @Controller
// @RequestMapping("/browse")
// @RestController("/browse")
// @CrossOrigin(origins = "http://localhost:4200")
// public class BrowseCardController {

// private JsonDataLoader jsonDataLoader = null;
// private List<Card> allCards = null;

// public BrowseCardController(AccountService accountService) {
// try {
// jsonDataLoader = new JsonDataLoader("c:/Dev/test_files/oracle-cards.json");
// allCards = jsonDataLoader.getCardList();

// } catch (IOException e) {
// throw new RuntimeException("Failed to load Cards", e);
// }
// }

// @GetMapping("/")
// public ModelAndView getBrowseView(HttpServletRequest request) {

// HttpSession session = request.getSession(false);

// if (session != null && session.getAttribute("user") != null) {
// return new ModelAndView("browse");
// }

// return new ModelAndView("redirect:/startup");
// }

// @GetMapping("/page")
// public ResponseEntity<List<Card>> getCards(@RequestParam(defaultValue = "0")
// int page,
// @RequestParam(defaultValue = "25") int size) {

// int start = Math.min(page * size, allCards.size());
// int end = Math.min(start + size, allCards.size());
// return new ResponseEntity<>(allCards.subList(start, end), HttpStatus.OK);
// }

// @GetMapping("/search")
// public ResponseEntity<List<Card>> searchCards(@RequestParam String query) {
// return new ResponseEntity<>(jsonDataLoader.findCardsByNameParralel(query),
// HttpStatus.OK);
// }

// @GetMapping("/totalPages")
// public ResponseEntity<Integer> getTotalPages(@RequestParam(defaultValue =
// "25") int size) {

// int totalCards = allCards.size();
// int totalPages = (int) Math.ceil((double) totalCards / size);

// return new ResponseEntity<>(totalPages, HttpStatus.OK);
// }

// }
