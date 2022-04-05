export const RoleType = {
  SystemAdmin: 'SYSTEM_ADMIN',
  Admin: 'ADMIN',
  User: 'USER',
} as const;
export type RoleType = typeof RoleType[keyof typeof RoleType];
export const AllRoleType = Object.values(RoleType);

export interface User {
  user_id: number
  user_name: string
  role_id: number
  role: RoleType
  token: string
}
