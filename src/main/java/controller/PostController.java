package controller;

import com.google.gson.Gson;
import model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/api/posts")
public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public void all(HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var data = service.all();
        final var gson = new Gson();
        response.getWriter().print(gson.toJson(data));
    }

    @GetMapping("/{id}")
    public void getById(@PathVariable long id, HttpServletResponse response) throws IOException {
        // TODO: deserialize request & serialize response
        response.setContentType(APPLICATION_JSON);
        final var data = service.getById(id);
        final var gson = new Gson();
        response.getWriter().print(gson.toJson(data));
    }

    @PostMapping
    public void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var gson = new Gson();
        final var post = gson.fromJson(request.getReader(), Post.class);
        final var data = service.save(post);
        response.getWriter().print(gson.toJson(data));
    }

    @DeleteMapping("/{id}")
    public void removeById(long id, HttpServletResponse response) throws IOException {
        // TODO: deserialize request & serialize response
        response.setContentType(APPLICATION_JSON);
        service.removeById(id);
        response.getWriter().print("Пост с id " + id + " удален");

    }
}
