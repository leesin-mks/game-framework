package com.game.command;

/**
 * @date 2020年03月04日 19:40
 * @auth leesin
 */
public final class CSProtocol
{
    public static final short PING = 0x0001;

    public static final short REGISTER = 0x0002;

    public static final short FORWARD_MESSAGE = 0x0003;

    public static final short MESSAGE_TO_USER = 0x0004;

    public static final short PLAYER_DISCONNECT = 0x0005;

    public static final short PLAYER_ON_DISCONNECT = 0x0006;
}
