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

public final class ProtocolOutProto {
  private ProtocolOutProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  /**
   * Protobuf enum {@code com.game.proto.ProtocolOut}
   */
  public enum ProtocolOut
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>PING = 20480;</code>
     *
     * <pre>
     * ping
     * </pre>
     */
    PING(0, 20480),
    /**
     * <code>USER_LOGIN = 20481;</code>
     *
     * <pre>
     * 登录
     * </pre>
     */
    USER_LOGIN(1, 20481),
    /**
     * <code>USER_LOGIN_OUT = 20482;</code>
     *
     * <pre>
     * 登出
     * </pre>
     */
    USER_LOGIN_OUT(2, 20482),
    /**
     * <code>SHUT_DOWN = 20483;</code>
     *
     * <pre>
     * 停服
     * </pre>
     */
    SHUT_DOWN(3, 20483),
    ;

    /**
     * <code>PING = 20480;</code>
     *
     * <pre>
     * ping
     * </pre>
     */
    public static final int PING_VALUE = 20480;
    /**
     * <code>USER_LOGIN = 20481;</code>
     *
     * <pre>
     * 登录
     * </pre>
     */
    public static final int USER_LOGIN_VALUE = 20481;
    /**
     * <code>USER_LOGIN_OUT = 20482;</code>
     *
     * <pre>
     * 登出
     * </pre>
     */
    public static final int USER_LOGIN_OUT_VALUE = 20482;
    /**
     * <code>SHUT_DOWN = 20483;</code>
     *
     * <pre>
     * 停服
     * </pre>
     */
    public static final int SHUT_DOWN_VALUE = 20483;


    public final int getNumber() { return value; }

    public static ProtocolOut valueOf(int value) {
      switch (value) {
        case 20480: return PING;
        case 20481: return USER_LOGIN;
        case 20482: return USER_LOGIN_OUT;
        case 20483: return SHUT_DOWN;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<ProtocolOut>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<ProtocolOut>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<ProtocolOut>() {
            public ProtocolOut findValueByNumber(int number) {
              return ProtocolOut.valueOf(number);
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
      return com.game.pb.command.ProtocolOutProto.getDescriptor().getEnumTypes().get(0);
    }

    private static final ProtocolOut[] VALUES = values();

    public static ProtocolOut valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private ProtocolOut(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:com.game.proto.ProtocolOut)
  }


  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\037proto/command/ProtocolOut.proto\022\016com.g" +
      "ame.proto*R\n\013ProtocolOut\022\n\n\004PING\020\200\240\001\022\020\n\n" +
      "USER_LOGIN\020\201\240\001\022\024\n\016USER_LOGIN_OUT\020\202\240\001\022\017\n\t" +
      "SHUT_DOWN\020\203\240\001B\'\n\023com.game.pb.commandB\020Pr" +
      "otocolOutProto"
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
