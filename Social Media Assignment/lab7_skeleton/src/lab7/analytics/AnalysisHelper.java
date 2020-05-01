/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7.analytics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lab7.entities.Comment;
import lab7.entities.Post;
import lab7.entities.User;

/**
 *
 * @author hp
 */
public class AnalysisHelper 
{
    // find user with Most Likes
    public void getUserWithMostLikes()
    {
        Map<Integer, Integer> userLikesCount = new HashMap<>();
        Map<Integer, User> users = DataStore.getInstance().getUsers();
    
        for (User user : users.values()) {
            for (Comment c : user.getComments()) {
                int likes = 0;
                if (userLikesCount.containsKey(user.getId())) {
                    likes = userLikesCount.get(user.getId());
                }
                likes += c.getLikes();
                userLikesCount.put(user.getId(), likes);
            }
        }
        int max = 0;
        int maxId = 0;
        for (int id : userLikesCount.keySet()) {
            if (userLikesCount.get(id) > max) {
                max = userLikesCount.get(id);
                maxId = id;
            }
        }
        System.out.println("User with most likes: " + max + "\n" 
            + users.get(maxId));
    }
    
    
    public void getFiveMostLikedComment() {
        Map<Integer, Comment> comments = DataStore.getInstance().getComments();
        List<Comment> commentList = new ArrayList<>(comments.values());
        
        Collections.sort(commentList, new Comparator<Comment>() {
            @Override 
            public int compare(Comment o1, Comment o2) {
                return o2.getLikes() - o1.getLikes();
            }
        });
        
        System.out.println("5 most likes comments: ");
        for (int i = 0; i < commentList.size() && i < 5; i++) {
            System.out.println(commentList.get(i));
        }
    }
    
    
    // 1. Average Number of Likes Per Comment
    
    public void getAverageNumberOfLikesPerComment() {
        Map<Integer, Comment> comments = DataStore.getDataStore().getComments();
        Map<Integer, Integer> averageNumberOfLikes = new HashMap<Integer, Integer>();
        int totalLikes = 0;
        for (Comment comment : comments.values()) {
            totalLikes = totalLikes + comment.getLikes();
        }

        float averageLikes = totalLikes / comments.size();
        System.out.println("1. Average number of likes per comment " + averageLikes);
    }
    
    
    
    //2. Post with most liked comments
    public void getMostCommentLikedPost(){
        Map<Integer, Integer> postCommentLikes= new HashMap<Integer, Integer>();
        Map<Integer,User> users= DataStore.getInstance().getUsers();
        Map<Integer,Post> post= DataStore.getInstance().getPosts();
        Map<Integer, Comment> comment= DataStore.getInstance().getComments();
        
        for(Post p: post.values()){
            for (Comment c : p.getComments()){
                int likes=0;
                if(postCommentLikes.containsKey(p.getPostId())){
                    likes= postCommentLikes.get(p.getPostId());
                }
                likes=likes + c.getLikes();
                postCommentLikes.put(p.getPostId(), likes);
            }
        }
        int max =0;
        int maxId=0;
        for(int id : postCommentLikes.keySet()){
            if(postCommentLikes.get(id)>max){
                max= postCommentLikes.get(id);
                maxId=id;
            }
        }
        System.out.println("2. Post with most liked comments: " + max + "\n" + post.get(maxId) );
    }
}
    
    
    
