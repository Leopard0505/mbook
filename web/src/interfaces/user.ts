export const RoleType = {
  SystemAdmin: 'system-admin',
  Admin: 'admin',
  User: 'user',
} as const;
export type RoleType = typeof RoleType[keyof typeof RoleType];
export const AllRoleType = Object.values(RoleType);

export interface User {
  userId: number
  name: string
  role: RoleType
  token: string
}
