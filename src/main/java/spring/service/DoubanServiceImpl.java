package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import spring.dao.CommentRedisRepository;
import spring.dto.Comment;
import spring.response.DoubanCode;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: yz<br/>
 * Date: 6/28/2019<br/>
 * Time: 5:12 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
@Service
public class DoubanServiceImpl implements DoubanService {

    @Autowired
    private CommentRedisRepository commentRedisRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Iterable<Comment> getAllUrls() {
        return commentRedisRepository.findAll();
    }

    @Override
    public void cleanAll() {
    }

    @Override
    public DoubanCode removeUrl(String url) {

        return null;
    }

    @Override
    public DoubanCode addUrl(Comment dto) {

        ArrayList<Long> ids = new ArrayList<>();
        Iterable<Comment> all = commentRedisRepository.findAll();
        for (Comment comment : all) {
            if (comment == null) {
                continue;
            }
            String url = dto.getUrl();
            String url1 = comment.getUrl();
            System.err.println("url1 = " + url1);
            if (url.equals(url1)) {
                System.err.println("duplicate found");
                return DoubanCode.DUPLIATED_URL;
            }
        }
        System.err.println("DoubanServiceImpl.addUrl adding new " + dto.getUrl());
        commentRedisRepository.findAll().forEach(o -> {
            if (o != null) {
                ids.add(o.getId());
            }
        });

        Optional<Long> max = ids.stream().max(Comparator.comparing(Long::valueOf));
        Long maxI = max.orElse(0L);
        dto.setId(maxI + 1);
        commentRedisRepository.save(dto);
        return DoubanCode.SUCCESS;
    }

    @Override
    public DoubanCode addUrlOld(String url) {
        if(findDuplicateUrl(url)) {
           return DoubanCode.DUPLIATED_URL;
        }
        redisTemplate.opsForValue().set(getNextUrlKey(), url);
        return DoubanCode.SUCCESS;
    }

    @Override
    public DoubanCode removeUrlOld(String url) {
        Set<String> keys = redisTemplate.keys("d*");
        HashMap<String, String> map = new HashMap<>();
        for (String key : keys) {
            String urlp = redisTemplate.opsForValue().get(key).toString();
            map.put(key, urlp);
            if(url.equals(urlp)){
                System.err.println("DoubanServiceImpl.removeUrlOld found existing url removing");
                redisTemplate.delete(key);
                return DoubanCode.SUCCESS;
            }
        }
        return DoubanCode.URL_NOT_FOUND;
    }

    @Override
    public List<String> getAllOld() {
        Set<String> keys = redisTemplate.keys("d*");
        List<String> urls = redisTemplate.opsForValue().multiGet(keys);
        return urls;
    }

    public boolean findDuplicateUrl(String url) {
        Set<String> keys = redisTemplate.keys("d*");
        List<String> urls = redisTemplate.opsForValue().multiGet(keys);
        return urls.contains(url);
    }

    public String getNextUrlKey() {
        Set<String> keys = redisTemplate.keys("d*");
        if (CollectionUtils.isEmpty(keys)) {
            return "d1";
        }
        Optional<String> max = keys.stream().filter(v -> Pattern.compile("d(\\d)+").matcher(v).matches()).map(v -> v.substring(1)).max(Comparator.comparing(Integer::valueOf));
        String value = max.orElse("0");
        Integer maxRedis = Integer.valueOf(value);
        maxRedis++;
        return "d" + maxRedis;
    }
}
