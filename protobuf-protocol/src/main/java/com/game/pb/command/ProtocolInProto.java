/*
 * Copyright 2016-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.game.pb.command;

public final class ProtocolInProto {
  private ProtocolInProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  /**
   * Protobuf enum {@code com.game.proto.ProtocolIn}
   */
  public enum ProtocolIn
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>PING = 1;</code>
     *
     * <pre>
     * ping
     * </pre>
     */
    PING(0, 1),
    /**
     * <code>USER_LOGIN = 2;</code>
     *
     * <pre>
     * 登录
     * </pre>
     */
    USER_LOGIN(1, 2),
    /**
     * <code>FORWARD_MESSAGE = 3;</code>
     *
     * <pre>
     * 转发消息
     * </pre>
     */
    FORWARD_MESSAGE(2, 3),
    ;

    /**
     * <code>PING = 1;</code>
     *
     * <pre>
     * ping
     * </pre>
     */
    public static final int PING_VALUE = 1;
    /**
     * <code>USER_LOGIN = 2;</code>
     *
     * <pre>
     * 登录
     * </pre>
     */
    public static final int USER_LOGIN_VALUE = 2;
    /**
     * <code>FORWARD_MESSAGE = 3;</code>
     *
     * <pre>
     * 转发消息
     * </pre>
     */
    public static final int FORWARD_MESSAGE_VALUE = 3;


    public final int getNumber() { return value; }

    public static ProtocolIn valueOf(int value) {
      switch (value) {
        case 1: return PING;
        case 2: return USER_LOGIN;
        case 3: return FORWARD_MESSAGE;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<ProtocolIn>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<ProtocolIn>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<ProtocolIn>() {
            public ProtocolIn findValueByNumber(int number) {
              return ProtocolIn.valueOf(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.game.pb.command.ProtocolInProto.getDescriptor().getEnumTypes().get(0);
    }

    private static final ProtocolIn[] VALUES = values();

    public static ProtocolIn valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private ProtocolIn(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:com.game.proto.ProtocolIn)
  }


  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\036proto/command/ProtocolIn.proto\022\016com.ga" +
      "me.proto*;\n\nProtocolIn\022\010\n\004PING\020\001\022\016\n\nUSER" +
      "_LOGIN\020\002\022\023\n\017FORWARD_MESSAGE\020\003B&\n\023com.gam" +
      "e.pb.commandB\017ProtocolInProto"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
