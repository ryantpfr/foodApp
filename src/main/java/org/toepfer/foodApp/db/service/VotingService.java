package org.toepfer.foodApp.db.service;

import org.toepfer.foodApp.db.entity.RestaurantEntity;
import org.toepfer.foodApp.db.entity.UserEntity;
import org.toepfer.foodApp.db.entity.VoteEntity;
import org.toepfer.foodApp.db.entity.VotingSession;
import org.toepfer.foodApp.db.repository.*;
import org.toepfer.foodApp.web.bean.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by toepfer on 8/24/2017.
 */
@Service
public class VotingService {

    @Autowired private SessionRepository sessionRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private VoteRepository voteRepository;
    @Autowired private RestaurantRepository restaurantRepository;
    @Autowired private RestaurantSelectorRepository restaurantSelectorRepository;

    public static final int NUM_RESTAURANTS =5;

    public List<Restaurant> getSessionRestaurants(){

        VotingSession votingSession = sessionRepository.findOneByActiveTrue();

        return votingSession.getRestaurantEntities().stream().map(this::restaurantEntityToRestaurantCountVotes).sorted().collect(Collectors.toList());

    }

    @Transactional
    public List<Restaurant> createSession(){

        VotingSession session = new VotingSession();
        session.setActive(true);

        List<String> restaurantNames = restaurantSelectorRepository.selectRestaurantOptions(NUM_RESTAURANTS);
        List<RestaurantEntity> restaurantEntities = getRestaurantEntities(session, restaurantNames);

        session.setRestaurantEntities(restaurantEntities);

        sessionRepository.markAllSessionsInactive();//mark all old sessions inactive
        sessionRepository.saveAndFlush(session);//insert new session

        return restaurantEntities.stream().map(r -> restaurantEntityToRestaurant(r,0)).collect(Collectors.toList());
    }

    @Transactional
    public void vote(int restaurantId, String username){

        UserEntity userEntity = userRepository.findOne(username);
        VotingSession votingSession = sessionRepository.findOneByActiveTrue();
        RestaurantEntity restaurantEntity = restaurantRepository.findOneByRestaurantIdAndVotingSession(restaurantId,votingSession);
        VoteEntity voteEntity = getVote(userEntity, votingSession);

        if(restaurantEntity != null){
            voteEntity.setRestaurantEntity(restaurantEntity);
        }else{
            //throw something
        }

        voteRepository.saveAndFlush(voteEntity);
    }

    private VoteEntity getVote(UserEntity userEntity, VotingSession votingSession){
        VoteEntity voteEntity = findExistingVoteByUser(userEntity,votingSession);

        if(voteEntity == null) {
            voteEntity = new VoteEntity();
            voteEntity.setUserEntity(userEntity);
        }
        return voteEntity;
    }

    private VoteEntity findExistingVoteByUser(UserEntity userEntity, VotingSession votingSession) {
        return voteRepository.findBySessionAndUser(userEntity,votingSession);
    }


    private Restaurant restaurantEntityToRestaurantCountVotes(RestaurantEntity restaurantEntity){

        int votes = voteRepository.countByRestaurantEntity(restaurantEntity);

        return restaurantEntityToRestaurant(restaurantEntity,votes);
    }


    private Restaurant restaurantEntityToRestaurant(RestaurantEntity restaurantEntity, int votes){
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantEntity.getRestaurantId());
        restaurant.setName(restaurantEntity.getName());
        restaurant.setVotes(votes);

        return restaurant;
    }

    private List<RestaurantEntity> getRestaurantEntities(VotingSession session, List<String> restaurantNames) {
        List<RestaurantEntity> restaurantEntities = new ArrayList<>();
        for(int i = 0; i < NUM_RESTAURANTS; i++){
            RestaurantEntity restaurantEntity = new RestaurantEntity();
            restaurantEntity.setName(restaurantNames.get(i));
            restaurantEntity.setVotingSession(session);
            restaurantEntities.add(restaurantEntity);
        }
        return restaurantEntities;
    }
}
