package com.game.type;

/**
 * @date 2020年03月31日 18:03
 * @auth zm
 */
public enum ServerType {
	GAME(1),      		 // 游戏服务区
	CENTER(2),         	 // 中央服务器
	GATE(3),  			 // 网关
	LOGIN(4),  			 // 登录
	RESOURCE(5);		 // 资源服务器



	private final int type;

	private ServerType(int type)
	{
		this.type = type;
	}

	public int getValue()
	{
		return type;
	}
}
