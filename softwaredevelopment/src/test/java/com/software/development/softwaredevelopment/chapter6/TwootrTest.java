package com.software.development.softwaredevelopment.chapter6;

import static com.software.development.softwaredevelopment.chapter6.TestData.TWOOT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import com.software.development.softwaredevelopment.chapter6.domain.Position;
import com.software.development.softwaredevelopment.chapter6.domain.twoot.Twoot;
import com.software.development.softwaredevelopment.chapter6.domain.twoot.TwootRepository;
import com.software.development.softwaredevelopment.chapter6.domain.user.UserRepository;
import com.software.development.softwaredevelopment.chapter6.enums.FollowStatus;
import com.software.development.softwaredevelopment.chapter6.enums.RegistrationStatus;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TwootrTest {

    private final ReceiverEndPoint receiverEndPoint = mock(ReceiverEndPoint.class);

    private Twootr twootr;
    private SenderEndPoint endPoint;
    @BeforeEach
    void setUp()
    {
    }

    @Test
    void shouldBeAbleToAuthenticateUser(){
        //유효 사용자의 로그온 메시지 수신

        //로그온 메서드는 새 엔드포인트 반환

        //엔트포인트 유효성을 확인하는 어서션션
    }

    @Test
    void shouldNotAuthenticateUserWithWrongPassword(){
        final Optional<SenderEndPoint> endPoint = twootr.onLogon(
            TestData.USER_ID, "bad password", receiverEndPoint);

        assertFalse(endPoint.isPresent());
    }

    @Test
    void shouldFollowValidUser() {
        logon();

        final FollowStatus followStatus = endPoint.onFollow(TestData.OTHER_USER_ID);

        assertEquals(FollowStatus.SUCCESS, followStatus);
    }

    @Test
    void shouldNotDuplicateFollowValidUser(){
        logon();

        endPoint.onFollow(TestData.OTHER_USER_ID);

        final FollowStatus followStatus = endPoint.onFollow(TestData.OTHER_USER_ID);
        assertEquals(FollowStatus.ALREADY_FOLLOWING, followStatus);
    }

    private void logon()
    {
        this.endPoint = logon(TestData.USER_ID, receiverEndPoint);
    }

    private SenderEndPoint logon(final String userId, final ReceiverEndPoint receiverEndPoint)
    {
        final Optional<SenderEndPoint> endPoint = twootr.onLogon(userId, TestData.PASSWORD, receiverEndPoint);
        assertTrue("Failed to logon", endPoint.isPresent());
        return endPoint.get();
    }

    @Test
    public void shouldReceiveTwootsFromFollowedUser()
    {
        final String id = "1";

        logon();

        endPoint.onFollow(TestData.OTHER_USER_ID);

        final SenderEndPoint otherEndPoint = otherLogon();
        otherEndPoint.onSendTwoot(id, TWOOT);

//        verify(twootRepository).add(id, TestData.OTHER_USER_ID, TWOOT);
//        verify(receiverEndPoint).onTwoot(new Twoot(id, TestData.OTHER_USER_ID, TWOOT, new Position(0)));

    }

    private SenderEndPoint otherLogon()
    {
        return logon(TestData.OTHER_USER_ID, mock(ReceiverEndPoint.class));
    }

    @Test
    public void shouldReceiveReplayOfTwootsAfterLogoff()
    {
        final String id = "1";

        userFollowsOtherUser();

        final SenderEndPoint otherEndPoint = otherLogon();
        otherEndPoint.onSendTwoot(id, TWOOT);

        logon();

        verify(receiverEndPoint).onTwoot(twootAt(id, POSITION_1));
    }

    private void userFollowsOtherUser()
    {
        logon();

        endPoint.onFollow(TestData.OTHER_USER_ID);

        endPoint.onLogoff();
    }

}
